package net.plorus.demo.multipleprovider.config.security.manager;

import net.plorus.demo.multipleprovider.config.security.MultipleProviderAuthentication;
import net.plorus.demo.multipleprovider.config.security.credential.AmandaCredential;
import net.plorus.demo.multipleprovider.config.security.principal.AmandaPrincipal;
import net.plorus.demo.multipleprovider.platform.amanda.controller.client.AmandaClient;
import net.plorus.demo.multipleprovider.platform.amanda.controller.client.dto.AmandaInternalResAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class AmandaAuthenticationManager implements AuthenticationManager {
    private final AmandaClient amandaClient;

    public AmandaAuthenticationManager(AmandaClient amandaClient) {
        this.amandaClient = amandaClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AmandaCredential amandaCredential = (AmandaCredential) authentication.getCredentials();

        AmandaInternalResAuthentication authenticationInfo = amandaClient.getAuthenticationInformation(amandaCredential);

        AmandaPrincipal amandaPrincipal = new AmandaPrincipal(authenticationInfo.getName(), authenticationInfo.getUid(), authenticationInfo.getAdult());

        return MultipleProviderAuthentication.authenticated(amandaPrincipal, null, List.of(new SimpleGrantedAuthority("ROLE_AMANDA_USER")));
    }
}
