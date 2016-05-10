package com.janusze.elista.user.dto;

import com.janusze.elista.utils.enums.EUserAuthority;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;
    private Date techDate;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private EUserAuthority role;
    private long expires;
    private EUserAuthority authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<EUserAuthority> singleKurwaAuthority = new HashSet<>();
        singleKurwaAuthority.add(authority);
        return singleKurwaAuthority;
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
        return true;
    }


}
