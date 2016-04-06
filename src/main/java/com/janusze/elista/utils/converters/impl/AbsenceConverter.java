package com.janusze.elista.utils.converters.impl;


import com.janusze.elista.absence.dto.AbsenceDTO;
import com.janusze.elista.absence.ob.AbsenceOB;
import com.janusze.elista.utils.converters.AbstractConverter;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Component
public class AbsenceConverter extends AbstractConverter<AbsenceOB, AbsenceDTO> {
    @Autowired
    public AbsenceConverter(Mapper mapper) {
        super(mapper, AbsenceOB.class, AbsenceDTO.class);
    }
}