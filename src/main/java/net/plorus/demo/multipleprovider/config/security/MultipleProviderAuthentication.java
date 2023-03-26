package net.plorus.demo.multipleprovider.config.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * SecurityContextHolder.SecurityContext의 Authentication 객체
 * UsernamePasswordAuthenticationToken과 거의 완전하게 동일함
 * 굳이 따로 만드는 이유는 class 이름 때문에 혼동이 생길 수 있어서 별도로 만듦
 * 이 class를 안만들고 UsernamePasswordAuthenticationToken을 사용해도
 * 구동 상 문제는 없음
 */
public class MultipleProviderAuthentication extends AbstractAuthenticationToken {
    private final Object principal;
    private Object credentials;

    public MultipleProviderAuthentication(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public MultipleProviderAuthentication(Object principal, Object credentials,
                                          Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    public static MultipleProviderAuthentication unauthenticated(Object principal, Object credentials) {
        return new MultipleProviderAuthentication(principal, credentials);
    }

    public static MultipleProviderAuthentication authenticated(Object principal, Object credentials,
                                                               Collection<? extends GrantedAuthority> authorities) {
        return new MultipleProviderAuthentication(principal, credentials, authorities);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated,
                "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
