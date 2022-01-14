package nl.averageflow.springwarehouse.domain.authentication.dto;

import nl.averageflow.springwarehouse.domain.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;

    public UserDetails(final User user) {
        this.user = user;
    }

    private static Collection<? extends GrantedAuthority> mapRolesToAuthorities(final Collection<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Collection<String> roles = new ArrayList<>();
        roles.add(this.user.getRole().getItemName());

        return UserDetails.mapRolesToAuthorities(roles);
    }
}
