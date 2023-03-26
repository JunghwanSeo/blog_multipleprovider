package net.plorus.demo.multipleprovider.config.security.manager;

import net.plorus.demo.multipleprovider.config.security.MultipleProviderAuthentication;
import net.plorus.demo.multipleprovider.config.security.credential.DrakeCredential;
import net.plorus.demo.multipleprovider.config.security.principal.DrakePrincipal;
import net.plorus.demo.multipleprovider.platform.drake.controller.client.DrakeClient;
import net.plorus.demo.multipleprovider.platform.drake.controller.client.dto.DrakeInternalResAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class DrakeAuthenticationManager implements AuthenticationManager {
    private final DrakeClient drakeClient;

    public DrakeAuthenticationManager(DrakeClient drakeClient) {
        this.drakeClient = drakeClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DrakeCredential drakeCredential = (DrakeCredential) authentication.getCredentials();

        // drake의 API를 통해 사용자 인증이 이뤄진다는 것을 보여주기 위한 부분일 뿐,
        // 실제 drakeClient.getAuthenticationInformation에는 별내용이 없습니다...
        DrakeInternalResAuthentication authenticationInfo = drakeClient.getAuthenticationInformation(drakeCredential);

        DrakePrincipal drakePrincipal = new DrakePrincipal(authenticationInfo.getName(), authenticationInfo.getUid());

        return MultipleProviderAuthentication.authenticated(drakePrincipal, null, List.of(new SimpleGrantedAuthority("ROLE_DRAKE_USER")));
    }
}
