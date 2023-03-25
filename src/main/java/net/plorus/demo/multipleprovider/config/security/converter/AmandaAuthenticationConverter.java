package net.plorus.demo.multipleprovider.config.security.converter;

import net.plorus.demo.multipleprovider.config.security.MultipleProviderAuthentication;
import net.plorus.demo.multipleprovider.config.security.credential.AmandaCredential;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AmandaAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        AmandaCredential amandaCredential = new AmandaCredential(request.getHeader(AUTHORIZATION));

        return MultipleProviderAuthentication.unauthenticated(amandaCredential, null);
    }
}
