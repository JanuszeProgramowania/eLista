package com.janusze.elista.utils.converters.impl;


import com.janusze.elista.schedule.dto.ScheduleDTO;
import com.janusze.elista.schedule.ob.ScheduleOB;

import com.janusze.elista.utils.converters.AbstractConverter;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */
@Component
public class ScheduleConverter extends AbstractConverter<ScheduleOB, ScheduleDTO> {
    @Autowired
    public ScheduleConverter(Mapper mapper) {
        super(mapper, ScheduleOB.class, ScheduleDTO.class);
    }
}