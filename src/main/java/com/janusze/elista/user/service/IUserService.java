package com.janusze.elista.user.service;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.dto.UserDetailsDTO;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
public interface IUserService {

    UserDTO findUserById(Long aId);

    List<UserDTO> findAllUsers();

    List<UserDTO> findUsersByName(String aName);

    List<UserDTO> findUsersByLastName(String aLastName);

    List<UserDTO> findUsersByNames(String aName, String aLastName);

    UserDTO findUserByEmail(String email);

    UserDTO saveUser(UserDTO aUserDTO);

    void changePassword(UserDetailsDTO aUserDetailsDTO, String aNewPassword);

    void deleteUser(Long aId);
}
