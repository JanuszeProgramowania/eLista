package com.janusze.elista.auth;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.utils.enums.EUserAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomasz Jodko on 2016-05-10.
 */
public class UserAuthentication implements Authentication {

    private final UserDTO userDTO;
    private boolean authenticated = true;

    public UserAuthentication(UserDTO user) {
        this.userDTO = user;
    }

    @Override
    public String getName() {
        return userDTO.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<EUserAuthority> singleKurwaAuthority = new HashSet<>();
        singleKurwaAuthority.add(userDTO.getAuthority());
        return singleKurwaAuthority;
    }


    @Override
    public Object getCredentials() {
        return userDTO.getPassword();
    }

    @Override
    public UserDTO getDetails() {
        return userDTO;
    }

    @Override
    public Object getPrincipal() {
        return userDTO.getEmail();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
