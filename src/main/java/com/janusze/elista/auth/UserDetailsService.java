package com.janusze.elista.auth;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Tomasz Jodko on 2016-05-10.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUserService userService;

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public final UserDTO loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserDTO pUserDTO = userService.findUserByEmail(email);
        if (pUserDTO == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(pUserDTO);
        return pUserDTO;
    }
}
