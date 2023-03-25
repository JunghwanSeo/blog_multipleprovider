package net.plorus.demo.multipleprovider.config.security.converter;

import net.plorus.demo.multipleprovider.config.security.MultipleProviderAuthentication;
import net.plorus.demo.multipleprovider.config.security.credential.DrakeCredential;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;

public class DrakeAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        DrakeCredential drakeCredential = new DrakeCredential(request.getHeader("cookie"));

        return MultipleProviderAuthentication.unauthenticated(drakeCredential, null);
    }
}
