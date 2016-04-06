package com.janusze.elista.utils.converters.impl;


import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.utils.converters.AbstractConverter;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Component
public class UserConverter extends AbstractConverter<UserOB, UserDTO> {
    @Autowired
    public UserConverter(Mapper mapper) {
        super(mapper, UserOB.class, UserDTO.class);
    }
}
