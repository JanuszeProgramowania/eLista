package com.janusze.elista.user.api;

import com.janusze.elista.user.dto.UserDTO;
import com.janusze.elista.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomasz Jodko on 2016-03-16.
 */

@RestController
@Transactional
@RequestMapping(value = "/elista/users")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(value = "getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Long aId) {
        return new ResponseEntity<>(userService.findUserById(aId), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findUsersByName(@PathVariable("name") String aName) {
        return new ResponseEntity<>(userService.findUsersByName(aName), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByLastName/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findUsersByLastName(@PathVariable("lastName") String aLastName) {
        return new ResponseEntity<>(userService.findUsersByLastName(aLastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/getByLastNames/{name},{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findUsersByNames(@PathVariable("name") String aName, @PathVariable("lastName") String aLastName) {
        return new ResponseEntity<>(userService.findUsersByNames(aName, aLastName), HttpStatus.OK);
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO aUserDTO) {
        return new ResponseEntity<>(userService.saveUser(aUserDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long aId) {
        userService.deleteUser(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteByNames/{name},{lastName}", method = RequestMethod.PUT)
    public ResponseEntity<Void> deleteUsersByNames(@PathVariable("name") String aName, @PathVariable("lastName") String aLastName) {
        List<UserDTO> pUsersToBeDeleted = userService.findUsersByNames(aName, aLastName);
        for (UserDTO user : pUsersToBeDeleted) {
            userService.deleteUser(user.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
