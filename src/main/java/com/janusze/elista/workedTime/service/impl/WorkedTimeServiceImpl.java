package com.janusze.elista.workedTime.service.impl;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.user.service.IUserService;
import com.janusze.elista.utils.converters.impl.UserConverter;
import com.janusze.elista.utils.converters.impl.WorkedTimeConverter;
import com.janusze.elista.utils.wrappers.WorkedTimeAndUserDTO;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;
import com.janusze.elista.workedTime.ob.WorkedTimeOB;
import com.janusze.elista.workedTime.repository.IWorkedTimeRepository;
import com.janusze.elista.workedTime.service.IWorkedTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@Service
@Transactional
public class WorkedTimeServiceImpl implements IWorkedTimeService {
    @Autowired
    IWorkedTimeRepository iWorkedTimeRepository;
    @Autowired
    WorkedTimeConverter workedTimeConverter;
    @Autowired
    IUserService userService;
    @Autowired
    UserConverter userConverter;

    @Override
    public WorkedTimeDTO findWorkedTimeById(Long aId) {
        WorkedTimeOB pWorkedTimeOB = iWorkedTimeRepository.findOne(aId);
        if (pWorkedTimeOB == null) {
            return null;
        }
        return workedTimeConverter.mapOBtoDTO(pWorkedTimeOB);
    }

    @Override
    public List<WorkedTimeDTO> findAllWorkedTimes() {
        List<WorkedTimeDTO> pResult = new ArrayList<>();
        List<WorkedTimeOB> pWorkedTimeList = iWorkedTimeRepository.findAll();
        for (WorkedTimeOB workedTime : pWorkedTimeList) {
            pResult.add(workedTimeConverter.mapOBtoDTO(workedTime));
        }
        return pResult;
    }

    @Override
    public List<WorkedTimeDTO> findWorkedTimesByUserId(Long aUserId) {
        List<WorkedTimeDTO> pResult = new ArrayList<>();
        List<WorkedTimeOB> pWorkedTimeList = iWorkedTimeRepository.findByUserId(aUserId);
        for (WorkedTimeOB workedTime : pWorkedTimeList) {
            pResult.add(workedTimeConverter.mapOBtoDTO(workedTime));
        }
        return pResult;
    }

    @Override
    public List<WorkedTimeDTO> findWorkedTimesByUserFullName(String aName, String aLastName) {
        List<WorkedTimeDTO> pResult = new ArrayList<>();
        List<WorkedTimeOB> pWorkedTimeList = iWorkedTimeRepository.findByUserFullName(aName, aLastName);
        for (WorkedTimeOB workedTime : pWorkedTimeList) {
            pResult.add(workedTimeConverter.mapOBtoDTO(workedTime));
        }
        return pResult;
    }

    @Override
    public WorkedTimeDTO saveWorkedTime(WorkedTimeAndUserDTO aWrapper) {
        WorkedTimeDTO pWorkedTimeDTO = aWrapper.getWorkedTime();
        UserDTO pUserDTO = aWrapper.getUser();
        if (pWorkedTimeDTO == null || pUserDTO == null) {
            return null;
        }
        WorkedTimeOB pWorkedTimeOB = iWorkedTimeRepository.findOne(pWorkedTimeDTO.getId());
        if (pWorkedTimeOB == null) {
            UserOB pUserOB = userConverter.mapDTOtoOB(userService.findUserById(pUserDTO.getId()));
            pWorkedTimeOB = workedTimeConverter.mapDTOtoOB(pWorkedTimeDTO);
            pWorkedTimeOB.setUser(pUserOB);
            return workedTimeConverter.mapOBtoDTO(iWorkedTimeRepository.save(pWorkedTimeOB));
        }
        pWorkedTimeOB.setDay(pWorkedTimeDTO.getDay());
        pWorkedTimeOB.setFinish(pWorkedTimeDTO.getFinish());
        pWorkedTimeOB.setStart(pWorkedTimeDTO.getStart());
        pWorkedTimeOB.setDescription(pWorkedTimeDTO.getDescription());
        return workedTimeConverter.mapOBtoDTO(iWorkedTimeRepository.save(pWorkedTimeOB));
    }

    @Override
    public void deleteWorkedTime(Long aId) {
        iWorkedTimeRepository.delete(aId);
    }
}