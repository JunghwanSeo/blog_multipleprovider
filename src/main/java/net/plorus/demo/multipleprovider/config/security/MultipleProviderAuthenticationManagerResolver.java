package net.plorus.demo.multipleprovider.config.security;

import net.plorus.demo.multipleprovider.config.security.manager.AmandaAuthenticationManager;
import net.plorus.demo.multipleprovider.config.security.manager.DrakeAuthenticationManager;
import net.plorus.demo.multipleprovider.platform.amanda.controller.client.AmandaClient;
import net.plorus.demo.multipleprovider.platform.drake.controller.client.DrakeClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Request별로 AuthenticationManager를 선택할 수 있도록 Spring Security에 추가된 새로운 기능
 * 여기에서는 managers를 선언을 한 뒤 resolve()에서 managers loop를 돌며 일치하는 path가 있는 경우
 * AuthenticationManager를 응답하는 형태로 구성되어 있음
 * 다른 구현 방법으로는 다음 링크를 참조 :: Guide to the AuthenticationManagerResolver in Spring Security
 * <a href="https://www.baeldung.com/spring-security-authenticationmanagerresolver">Baeldung</a>
 * <p>
 * 각 서비스의 AuthenticationManager에 의존성이 필요함. AuthenticationManagerResolver에서 의존성을 받아 각 서비스
 * 의 AuthenticationManager에 주입하려고 하면 초기화 오류가 발생. 여기서 구현한 방법은
 * MultipleProviderAuthenticationManagerResolver 객체를 초기화 할 때 각 서비스에서 필요한 의존성을 받아 managers
 * 를 초기화를 진행하도록 했음.
 */
@Component
public class MultipleProviderAuthenticationManagerResolver implements AuthenticationManagerResolver<HttpServletRequest> {
    private final Map<RequestMatcher, AuthenticationManager> managers;

    public MultipleProviderAuthenticationManagerResolver(AmandaClient amandaClient,
                                                         DrakeClient drakeClient) {
        this.managers = Map.of(
                new AntPathRequestMatcher("/amanda/**"), new AmandaAuthenticationManager(amandaClient),
                new AntPathRequestMatcher("/drake/**"), new DrakeAuthenticationManager(drakeClient)
        );
    }

    @Override
    public AuthenticationManager resolve(HttpServletRequest request) {
        for (Map.Entry<RequestMatcher, AuthenticationManager> entry : managers.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }

        return null;
    }
}
