package com.janusze.elista.workedTime.service;

import com.janusze.elista.utils.wrappers.WorkedTimeAndUserDTO;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
public interface IWorkedTimeService {

    WorkedTimeDTO findWorkedTimeById(Long aId);

    List<WorkedTimeDTO> findAllWorkedTimes();

    List<WorkedTimeDTO> findWorkedTimesByUserId(Long aUserId);

    List<WorkedTimeDTO> findWorkedTimesByUserFullName(String aName, String aLastName);

    WorkedTimeDTO saveWorkedTime(WorkedTimeAndUserDTO aWrapper);

    void deleteWorkedTime(Long aId);
}