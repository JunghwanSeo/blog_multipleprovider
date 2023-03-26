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

        // amanda의 API를 통해 사용자 인증이 이뤄진다는 것을 보여주기 위한 부분일 뿐,
        // 실제 amandaClient.getAuthenticationInformation에는 별내용이 없습니다...
        AmandaInternalResAuthentication authenticationInfo = amandaClient.getAuthenticationInformation(amandaCredential);

        AmandaPrincipal amandaPrincipal = new AmandaPrincipal(authenticationInfo.getName(), authenticationInfo.getUid(), authenticationInfo.getAdult());

        return MultipleProviderAuthentication.authenticated(amandaPrincipal, null, List.of(new SimpleGrantedAuthority("ROLE_AMANDA_USER")));
    }
}
