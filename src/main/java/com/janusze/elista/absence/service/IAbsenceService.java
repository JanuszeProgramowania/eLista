package com.janusze.elista.absence.service;

import com.janusze.elista.absence.dto.AbsenceDTO;
import com.janusze.elista.utils.wrappers.AbsenceAndUserDTO;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
public interface IAbsenceService {

    AbsenceDTO findAbsenceById(Long aId);

    List<AbsenceDTO> findAllAbsences();

    List<AbsenceDTO> findAbsencesByUserId(Long aUserId);

    List<AbsenceDTO> findAbsencesByUserFullName(String aName, String aLastName);

    AbsenceDTO saveAbsence(AbsenceAndUserDTO aWrapper);

    void deleteAbsence(Long aId);
}
