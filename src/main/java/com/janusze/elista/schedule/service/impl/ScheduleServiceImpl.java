package com.janusze.elista.schedule.service.impl;

import com.janusze.elista.schedule.dto.ScheduleDTO;
import com.janusze.elista.schedule.ob.ScheduleOB;
import com.janusze.elista.schedule.repository.IScheduleRepository;
import com.janusze.elista.schedule.service.IScheduleService;
import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.user.service.IUserService;
import com.janusze.elista.utils.converters.impl.ScheduleConverter;
import com.janusze.elista.utils.converters.impl.UserConverter;
import com.janusze.elista.utils.wrappers.ScheduleAndUserDTO;
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
public class ScheduleServiceImpl implements IScheduleService {
    @Autowired
    IScheduleRepository iScheduleRepository;
    @Autowired
    ScheduleConverter scheduleConverter;
    @Autowired
    IUserService userService;
    @Autowired
    UserConverter userConverter;

    @Override
    public ScheduleDTO findScheduleById(Long aId) {
        ScheduleOB pScheduleOB = iScheduleRepository.findOne(aId);
        if (pScheduleOB == null) {
            return null;
        }
        return scheduleConverter.mapOBtoDTO(pScheduleOB);
    }

    @Override
    public List<ScheduleDTO> findAllSchedules() {
        List<ScheduleDTO> pResult = new ArrayList<>();
        List<ScheduleOB> pScheduleList = iScheduleRepository.findAll();
        for (ScheduleOB schedule : pScheduleList) {
            pResult.add(scheduleConverter.mapOBtoDTO(schedule));
        }
        return pResult;
    }

    @Override
    public List<ScheduleDTO> findSchedulesByUserId(Long aUserId) {
        List<ScheduleDTO> pResult = new ArrayList<>();
        List<ScheduleOB> pScheduleList = iScheduleRepository.findByUserId(aUserId);
        for (ScheduleOB schedule : pScheduleList) {
            pResult.add(scheduleConverter.mapOBtoDTO(schedule));
        }
        return pResult;
    }

    @Override
    public List<ScheduleDTO> findSchedulesByUserFullName(String aName, String aLastName) {
        List<ScheduleDTO> pResult = new ArrayList<>();
        List<ScheduleOB> pScheduleList = iScheduleRepository.findByUserFullName(aName, aLastName);
        for (ScheduleOB schedule : pScheduleList) {
            pResult.add(scheduleConverter.mapOBtoDTO(schedule));
        }
        return pResult;
    }

    @Override
    public ScheduleDTO saveSchedule(ScheduleAndUserDTO aWrapper) {
        ScheduleDTO pScheduleDTO = aWrapper.getSchedule();
        UserDTO pUserDTO = aWrapper.getUser();
        if (pScheduleDTO == null || pUserDTO == null) {
            return null;
        }
        ScheduleOB pScheduleOB = iScheduleRepository.findOne(pScheduleDTO.getId());
        if (pScheduleOB == null) {
            UserOB pUserOB = userConverter.mapDTOtoOB(userService.findUserById(pUserDTO.getId()));
            pScheduleOB = scheduleConverter.mapDTOtoOB(pScheduleDTO);
            pScheduleOB.setUser(pUserOB);
            return scheduleConverter.mapOBtoDTO(iScheduleRepository.save(pScheduleOB));
        }
        pScheduleOB.setDayOfTheWeek(pScheduleDTO.getDayOfTheWeek());
        pScheduleOB.setStart(pScheduleDTO.getStart());
        pScheduleOB.setFinish(pScheduleDTO.getFinish());
        return scheduleConverter.mapOBtoDTO(iScheduleRepository.save(pScheduleOB));
    }

    @Override
    public void deleteSchedule(Long aId) {
        iScheduleRepository.delete(aId);
    }
}