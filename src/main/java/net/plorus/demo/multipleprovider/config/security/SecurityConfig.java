package net.plorus.demo.multipleprovider.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 아래 설정된 SecurityFilterChain은 다중 서비스 인증을 위한 설정만 등록되어 있습니다.
 * 실사용을 위해서는 최소한 ExceptionHandling 정도는 추가로 등록해야 합니다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final MultipleProviderAuthenticationManagerResolver multipleProviderAuthenticationManagerResolver;

    public SecurityConfig(MultipleProviderAuthenticationManagerResolver multipleProviderAuthenticationManagerResolver) {
        this.multipleProviderAuthenticationManagerResolver = multipleProviderAuthenticationManagerResolver;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .anyRequest().authenticated())
                .build();
    }

    private AuthenticationFilter getAuthenticationFilter() {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(multipleProviderAuthenticationManagerResolver, new MultipleProviderAuthenticationConverterResolver());
        authenticationFilter.setSuccessHandler((request, response, authentication) -> {
        });

        return authenticationFilter;
    }
}
