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
