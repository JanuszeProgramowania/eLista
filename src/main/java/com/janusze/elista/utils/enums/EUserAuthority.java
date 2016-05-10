package com.janusze.elista.utils.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
public enum EUserAuthority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    public String getAuthority() {
        return name();
    }
}