package com.janusze.elista.absence.service.impl;

import com.janusze.elista.absence.dto.AbsenceDTO;
import com.janusze.elista.absence.ob.AbsenceOB;
import com.janusze.elista.absence.repository.IAbsenceRepository;
import com.janusze.elista.absence.service.IAbsenceService;
import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.ob.UserOB;
import com.janusze.elista.user.service.IUserService;
import com.janusze.elista.utils.converters.impl.AbsenceConverter;
import com.janusze.elista.utils.converters.impl.UserConverter;
import com.janusze.elista.utils.wrappers.AbsenceAndUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@Service
@Transactional
public class AbsenceServiceImpl implements IAbsenceService {
    @Autowired
    IAbsenceRepository iAbsenceRepository;
    @Autowired
    AbsenceConverter absenceConverter;
    @Autowired
    IUserService userService;
    @Autowired
    UserConverter userConverter;

    @Override
    public AbsenceDTO findAbsenceById(Long aId) {
        AbsenceOB pAbsenceOB = iAbsenceRepository.findOne(aId);
        if (pAbsenceOB == null) {
            return null;
        }
        return absenceConverter.mapOBtoDTO(pAbsenceOB);
    }

    @Override
    public List<AbsenceDTO> findAllAbsences() {
        List<AbsenceDTO> pResult = new ArrayList<>();
        List<AbsenceOB> pAbsenceList = iAbsenceRepository.findAll();
        for (AbsenceOB absence : pAbsenceList) {
            pResult.add(absenceConverter.mapOBtoDTO(absence));
        }
        return pResult;
    }

    @Override
    public List<AbsenceDTO> findAbsencesByUserId(Long aUserId) {
        List<AbsenceDTO> pResult = new ArrayList<>();
        List<AbsenceOB> pAbsenceList = iAbsenceRepository.findByUserId(aUserId);
        for (AbsenceOB absence : pAbsenceList) {
            pResult.add(absenceConverter.mapOBtoDTO(absence));
        }
        return pResult;
    }

    @Override
    public List<AbsenceDTO> findAbsencesByUserFullName(String aName, String aLastName) {
        List<AbsenceDTO> pResult = new ArrayList<>();
        List<AbsenceOB> pAbsenceList = iAbsenceRepository.findByUserFullName(aName, aLastName);
        for (AbsenceOB absence : pAbsenceList) {
            pResult.add(absenceConverter.mapOBtoDTO(absence));
        }
        return pResult;
    }

    @Override
    public AbsenceDTO saveAbsence(AbsenceAndUserDTO aWrapper) {
        AbsenceDTO pAbsenceDTO = aWrapper.getAbsence();
        UserDTO pUserDTO = aWrapper.getUser();
        if (pAbsenceDTO == null || pUserDTO == null) {
            return null;
        }
        AbsenceOB pAbsenceOB = iAbsenceRepository.findOne(pAbsenceDTO.getId());
        if (pAbsenceOB == null) {
            UserOB pUserOB = userConverter.mapDTOtoOB(userService.findUserById(pUserDTO.getId()));
            pAbsenceOB = absenceConverter.mapDTOtoOB(pAbsenceDTO);
            pAbsenceOB.setUser(pUserOB);
            return absenceConverter.mapOBtoDTO(iAbsenceRepository.save(pAbsenceOB));
        }
        pAbsenceOB.setDate(pAbsenceDTO.getDate());
        pAbsenceOB.setType(pAbsenceDTO.getType());
        pAbsenceOB.setHours(pAbsenceDTO.getHours());
        return absenceConverter.mapOBtoDTO(iAbsenceRepository.save(pAbsenceOB));
    }

    @Override
    public void deleteAbsence(Long aId) {
        iAbsenceRepository.delete(aId);
    }
}
