package com.epam.mentoring.homework.banking.controller;

import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.service.IUserService;
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

    /**
     * Get all users.
     * @return list with all {@link User}s.
     */
    @RequestMapping(value = {"/all", "/all/"}, method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.readAll();
    }

    /**
     * Updates user.
     * @param user user to update.
     * @return updated {@link User} instance.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.update(user), HttpStatus.OK);
    }

    /**
     * Gets user by its id.
     * @param userId user id.
     * @return {@link User} instance.
     */
    @RequestMapping(value = "/{userId}/", method = RequestMethod.GET, produces="application/json")
    public User getUserById(@PathVariable("userId") Integer userId) {
        return userService.read(userId);
    }

    /**
     * Deletes user by its id.
     * @param userId user id.
     * @return removed {@link User}.
     */
    @RequestMapping(value = "/{userId}/", method = RequestMethod.DELETE, produces="application/json")
    public User deleteUserById(@PathVariable("userId") Integer userId) {
        return userService.delete(userId);
    }

    /**
     * Creates new user.
     * @param user user.
     * @return created {@link User} instance.
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.store(user), HttpStatus.OK);
    }

}
