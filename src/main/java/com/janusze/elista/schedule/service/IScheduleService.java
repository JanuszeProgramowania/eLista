package com.janusze.elista.schedule.service;

import com.janusze.elista.schedule.dto.ScheduleDTO;
import com.janusze.elista.utils.wrappers.ScheduleAndUserDTO;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
public interface IScheduleService {

    ScheduleDTO findScheduleById(Long aId);

    List<ScheduleDTO> findAllSchedules();

    List<ScheduleDTO> findSchedulesByUserId(Long aUserId);

    List<ScheduleDTO> findSchedulesByUserFullName(String aName, String aLastName);

    ScheduleDTO saveSchedule(ScheduleAndUserDTO aWrapper);

    void deleteSchedule(Long aId);
}
