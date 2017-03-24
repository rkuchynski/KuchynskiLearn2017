package com.epam.mentoring.homework3.banking.controller;

import com.epam.mentoring.homework3.banking.domain.User;
import com.epam.mentoring.homework3.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User rest controller.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    @Qualifier("userServiceLoggingProxy")
    private IUserService userService;

    @RequestMapping(value = {"/all", "/all/"}, method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.readAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/", method = RequestMethod.GET, produces="application/json")
    public User getUserById(@PathVariable("userId") String userId) {
        return userService.read(userId);
    }

    @RequestMapping(value = "/{userId}/", method = RequestMethod.DELETE, produces="application/json")
    public User deleteUserById(@PathVariable("userId") String userId) {
        return userService.delete(userId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.store(user), HttpStatus.OK);
    }

}
