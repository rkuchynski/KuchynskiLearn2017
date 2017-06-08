package com.epam.mentoring.homework.banking.controller;

import com.epam.mentoring.homework.banking.service.IAccountService;
import com.epam.mentoring.homework.banking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to clear DB by user request.
 * <p/>
 * Date: 04/04/2017
 *
 * @author Raman Kuchynski
 */
@RestController
public class FlushDatabaseRestController {

    @Autowired
    @Qualifier("userServiceLoggingProxy")
    private IUserService userService;

    @Autowired
    @Qualifier("accountServiceLoggingProxy")
    private IAccountService accountService;

    /**
     * Flush embedded DB.
     */
    @RequestMapping(value = {"/flush_database"}, method = RequestMethod.GET)
    public void flushDatabase() {
        accountService.deleteAll();
        userService.deleteAll();
    }

}
