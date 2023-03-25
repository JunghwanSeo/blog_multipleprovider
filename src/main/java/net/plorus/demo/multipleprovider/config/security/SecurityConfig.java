package net.plorus.demo.multipleprovider.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
