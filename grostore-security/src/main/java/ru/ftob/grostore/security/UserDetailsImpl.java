package ru.ftob.grostore.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ftob.grostore.model.account.Account;
import ru.ftob.grostore.model.account.Role;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String email;
    private String password;
    private Collection<Role> roles;
    private Boolean enabled;

    public UserDetailsImpl() {

    }

    public UserDetailsImpl(final String email, final String password,
                           final Collection<Role> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = true;
    }

    UserDetailsImpl(Account account) {
        email = account.getEmail();
        password = account.getPassword();
        roles = account.getRoles();
        enabled = account.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
