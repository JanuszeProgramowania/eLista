package com.janusze.elista.auth;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 * Created by Tomasz Jodko on 2016-05-10.
 */
@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private static final long TEN_DAYS = 1000 * 60 * 60 * 24 * 10;

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationService(@Value("${token.secret}") String secret) {
        tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final UserDetailsDTO user = authentication.getDetails();
        user.setExpires(System.currentTimeMillis() + TEN_DAYS);
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public UserAuthentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final UserDTO userDTO = tokenHandler.parseUserFromToken(token);
            if (userDTO != null) {
                return new UserAuthentication(userDTO);
            }
        }
        return null;
    }
}