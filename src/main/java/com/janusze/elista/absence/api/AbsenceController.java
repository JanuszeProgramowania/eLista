package com.janusze.elista.absence.api;

import com.janusze.elista.absence.dto.AbsenceDTO;
import com.janusze.elista.absence.service.IAbsenceService;
import com.janusze.elista.utils.wrappers.AbsenceAndUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-29.
 */
@RestController
@Transactional
@RequestMapping(value = "/elista/absences")
public class AbsenceController {

    @Autowired
    IAbsenceService absenceService;

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<AbsenceDTO> findAbsenceById(@PathVariable("id") Long aId) {
        return new ResponseEntity<>(absenceService.findAbsenceById(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<AbsenceDTO>> findAllAbsences() {
        return new ResponseEntity<>(absenceService.findAllAbsences(), HttpStatus.OK);
    }

    @RequestMapping(value = "getByUserId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<AbsenceDTO>> findAbsencesByUserId(@PathVariable("userId") Long aId) {
        return new ResponseEntity<>(absenceService.findAbsencesByUserId(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByFullName/{userName},{userLastName}", method = RequestMethod.GET)
    public ResponseEntity<List<AbsenceDTO>> findAbsencesByUserFullName(@PathVariable("userName") String aName, @PathVariable("userLastName") String aLastName) {
        return new ResponseEntity<>(absenceService.findAbsencesByUserFullName(aName, aLastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveAbsence", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<AbsenceDTO> saveAbsence(@RequestBody AbsenceAndUserDTO wrapper) {
        return new ResponseEntity<>(absenceService.saveAbsence(wrapper), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteAbsence(@PathVariable("id") Long aId) {
        absenceService.deleteAbsence(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
