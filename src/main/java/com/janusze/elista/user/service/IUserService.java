package com.janusze.elista.user.service;

import com.janusze.elista.user.dto.UserDTO;

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

    UserDTO saveUser(UserDTO aUserDTO);

    void deleteUser(Long aId);
}
