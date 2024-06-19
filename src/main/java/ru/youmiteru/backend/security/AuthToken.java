package ru.youmiteru.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.youmiteru.backend.domain.Role;

import java.util.Collection;
import java.util.List;

public class AuthToken implements Authentication {
    private final Principal principal;
    private String roles;

    public AuthToken(Principal principal, String roles) {
        this.principal = principal;
        this.roles = "ROLE_" + roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles));
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return principal.email();
    }
}
