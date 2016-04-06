package com.janusze.elista.user.service.impl;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.user.repository.IUserRepository;
import com.janusze.elista.user.service.IUserService;
import com.janusze.elista.utils.converters.impl.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    UserConverter userConverter;

    @Override
    public UserDTO findUserById(Long aId) {
        UserOB pUserOB = iUserRepository.findOne(aId);
        if (pUserOB == null) {
            return null;
        }
        return userConverter.mapOBtoDTO(pUserOB);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<UserDTO> pResult = new ArrayList<>();
        List<UserOB> pUserList = iUserRepository.findAll();
        for (UserOB user : pUserList) {
            pResult.add(userConverter.mapOBtoDTO(user));
        }
        return pResult;
    }

    @Override
    public List<UserDTO> findUsersByName(String aName) {
        List<UserDTO> pResult = new ArrayList<>();
        List<UserOB> pUserList = iUserRepository.findByNameStartsWith(aName);
        for (UserOB user : pUserList) {
            pResult.add(userConverter.mapOBtoDTO(user));
        }
        return pResult;
    }

    @Override
    public List<UserDTO> findUsersByLastName(String aLastName) {
        List<UserDTO> pResult = new ArrayList<>();
        List<UserOB> pUserList = iUserRepository.findByLastNameStartsWith(aLastName);
        for (UserOB user : pUserList) {
            pResult.add(userConverter.mapOBtoDTO(user));
        }
        return pResult;
    }

    @Override
    public List<UserDTO> findUsersByNames(String aName, String aLastName) {
        List<UserDTO> pResult = new ArrayList<>();
        List<UserOB> pUserList = iUserRepository.findByFullName(aName, aLastName);
        for (UserOB user : pUserList) {
            pResult.add(userConverter.mapOBtoDTO(user));
        }
        return pResult;
    }

    @Override
    public UserDTO saveUser(UserDTO aUserDTO) {
        if (aUserDTO == null) {
            return null;
        }
        // sprawdzenie czy rekord juz istnieje
        UserOB pUserOB = aUserDTO.getId() == null ? null : iUserRepository.findOne(aUserDTO.getId());
        // zapis nowego
        if (pUserOB == null) {
            return userConverter.mapOBtoDTO(iUserRepository.save(userConverter.mapDTOtoOB(aUserDTO)));
        }
        // edycja istniejacego
        pUserOB.setName(aUserDTO.getName());
        pUserOB.setLastName(aUserDTO.getLastName());
        return userConverter.mapOBtoDTO(iUserRepository.save(pUserOB));
    }

    @Override
    public void deleteUser(Long aId) {
        iUserRepository.delete(aId);
    }
}
