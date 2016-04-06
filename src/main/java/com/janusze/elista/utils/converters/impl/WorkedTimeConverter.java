package com.janusze.elista.utils.converters.impl;


import com.janusze.elista.utils.converters.AbstractConverter;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;
import com.janusze.elista.workedTime.ob.WorkedTimeOB;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Component
public class WorkedTimeConverter extends AbstractConverter<WorkedTimeOB, WorkedTimeDTO> {
    @Autowired
    public WorkedTimeConverter(Mapper mapper) {
        super(mapper, WorkedTimeOB.class, WorkedTimeDTO.class);
    }
}