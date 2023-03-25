package net.plorus.demo.multipleprovider.config.security;

import net.plorus.demo.multipleprovider.config.security.converter.AmandaAuthenticationConverter;
import net.plorus.demo.multipleprovider.config.security.converter.DrakeAuthenticationConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class MultipleProviderAuthenticationConverterResolver implements AuthenticationConverter {
    private final Map<RequestMatcher, AuthenticationConverter> converters = Map.of(
            new AntPathRequestMatcher("/amanda/**"), new AmandaAuthenticationConverter(),
            new AntPathRequestMatcher("/drake/**"), new DrakeAuthenticationConverter()
    );

    @Override
    public Authentication convert(HttpServletRequest request) {
        for (Map.Entry<RequestMatcher, AuthenticationConverter> entry : converters.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue().convert(request);
            }
        }

        return null;
    }
}
