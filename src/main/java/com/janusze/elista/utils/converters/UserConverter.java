package com.janusze.elista.utils.converters;


import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
public class UserConverter {

    public static UserOB userDTOtoOB(UserDTO aUserDTO) {
        if (aUserDTO == null) {
            return null;
        }
        return new UserOB(aUserDTO.getName(), aUserDTO.getLastName());
    }

    public static UserDTO userOBtoDTO(UserOB aUserOB) {
        if (aUserOB == null) {
            return null;
        }
        return new UserDTO(aUserOB.getId(), aUserOB.getTechDate(), aUserOB.getName(), aUserOB.getLastName());
    }
}
