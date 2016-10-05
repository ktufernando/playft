package ar.com.jf.antilavado.security.user;

import ar.com.jf.antilavado.repository.model.Authority;
import ar.com.jf.antilavado.repository.model.User;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * UserSecurity.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 07/04/2015.
 */
public class UserSecurity extends User implements UserDetails {

    public UserSecurity(User user) {
        this.setLogin(user.getLogin());
        this.setPassword(user.getPassword());
        this.setEnabled(user.isEnabled());
        this.setAccountNonExpired(user.isAccountNonExpired());
        this.setAccountNonLocked(user.isAccountNonLocked());
        this.setCredentialsNonExpired(user.isCredentialsNonExpired());
        this.setUserAuthorities(user.getUserAuthorities());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        Set<Authority> userAuthorities = this.getUserAuthorities();
        if (CollectionUtils.isNotEmpty(userAuthorities)) {
            for (Authority userAuthority : userAuthorities) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userAuthority.getName());
                grantedAuthorities.add(authority);
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

}
