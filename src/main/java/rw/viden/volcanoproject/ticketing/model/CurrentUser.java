package rw.viden.volcanoproject.ticketing.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Viden ltd on 01/06/2016.
 */
public class CurrentUser implements UserDetails{
    private Users user;

    public CurrentUser(Users user) {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthorities(user);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
        return user.isEnabled()
                ;
    }
    private Collection<GrantedAuthority> getAuthorities(Users user) {
        List<GrantedAuthority> authList = getGrantedAuthorities(user.getRole());
        return authList;
    }

    private List<GrantedAuthority> getGrantedAuthorities(
            Role role) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(role.name()));

        return authorities;
    }
}
