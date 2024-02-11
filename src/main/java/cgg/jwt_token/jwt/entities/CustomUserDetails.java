package cgg.jwt_token.jwt.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    public User u1;

    public CustomUserDetails(User byUsername) {

        u1 = byUsername;
        // TODO Auto-generated constructor stub
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();

        authorities.add((new SimpleGrantedAuthority(u1.getRole())));
        return authorities;
    }

    @Override
    public String getPassword() {

        return u1.getPassword();
        // TODO Auto-generate
    }

    @Override
    public String getUsername() {
        return u1.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
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

}
