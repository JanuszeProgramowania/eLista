package com.janusze.elista.workedTime.api;

import com.janusze.elista.utils.wrappers.WorkedTimeAndUserDTO;
import com.janusze.elista.workedTime.dto.WorkedTimeDTO;
import com.janusze.elista.workedTime.service.IWorkedTimeService;
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
@RequestMapping(value = "/elista/workedTimes")
public class WorkedTimeController {

    @Autowired
    IWorkedTimeService workedTimeService;

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<WorkedTimeDTO> findWorkedTimeById(@PathVariable("id") Long aId) {
        return new ResponseEntity<>(workedTimeService.findWorkedTimeById(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<WorkedTimeDTO>> findAllWorkedTimes() {
        return new ResponseEntity<>(workedTimeService.findAllWorkedTimes(), HttpStatus.OK);
    }

    @RequestMapping(value = "getByUserId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkedTimeDTO>> findWorkedTimesByUserId(@PathVariable("userId") Long aId) {
        return new ResponseEntity<>(workedTimeService.findWorkedTimesByUserId(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByFullName/{userName},{userLastName}", method = RequestMethod.GET)
    public ResponseEntity<List<WorkedTimeDTO>> findWorkedTimesByUserFullName(@PathVariable("userName") String aName, @PathVariable("userLastName") String aLastName) {
        return new ResponseEntity<>(workedTimeService.findWorkedTimesByUserFullName(aName, aLastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveWorkedTime", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<WorkedTimeDTO> saveWorkedTime(@RequestBody WorkedTimeAndUserDTO workedTimeAndUserWrapper) {
        return new ResponseEntity<>(workedTimeService.saveWorkedTime(workedTimeAndUserWrapper), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteWorkedTime(@PathVariable("id") Long aId) {
        workedTimeService.deleteWorkedTime(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}