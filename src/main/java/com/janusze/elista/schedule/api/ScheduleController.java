package com.janusze.elista.schedule.api;

import com.janusze.elista.schedule.dto.ScheduleDTO;
import com.janusze.elista.schedule.service.IScheduleService;
import com.janusze.elista.utils.wrappers.ScheduleAndUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-30.
 */
@RestController
@Transactional
@RequestMapping(value = "/elista/schedules")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<ScheduleDTO> findScheduleById(@PathVariable("id") Long aId) {
        return new ResponseEntity<>(scheduleService.findScheduleById(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleDTO>> findAllSchedules() {
        return new ResponseEntity<>(scheduleService.findAllSchedules(), HttpStatus.OK);
    }

    @RequestMapping(value = "getByUserId/{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByUserId(@PathVariable("userId") Long aId) {
        return new ResponseEntity<>(scheduleService.findSchedulesByUserId(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByFullName/{userName},{userLastName}", method = RequestMethod.GET)
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByUserFullName(@PathVariable("userName") String aName, @PathVariable("userLastName") String aLastName) {
        return new ResponseEntity<>(scheduleService.findSchedulesByUserFullName(aName, aLastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveSchedule", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScheduleDTO> saveSchedule(@RequestBody ScheduleAndUserDTO scheduleAndUserWrapper) {
        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleAndUserWrapper), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteSchedule(@PathVariable("id") Long aId) {
        scheduleService.deleteSchedule(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}