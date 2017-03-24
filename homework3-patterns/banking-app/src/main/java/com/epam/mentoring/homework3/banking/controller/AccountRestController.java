package com.epam.mentoring.homework3.banking.controller;

import com.epam.mentoring.homework3.banking.domain.Account;
import com.epam.mentoring.homework3.banking.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Account rest controller.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@RestController
@RequestMapping("/account")
public class AccountRestController {

    @Autowired
    @Qualifier("accountServiceLoggingProxy")
    private IAccountService accountService;

    @RequestMapping(value = {"/all", "/all/"}, method = RequestMethod.GET)
    public List<Account> getAll() {
        return accountService.readAll();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.update(account);
    }

    @RequestMapping(value = "/{accountId}/", method = RequestMethod.GET, produces="application/json")
    public Account getAccountById(@PathVariable("accountId") String accountId) {
        return accountService.read(accountId);
    }

    @RequestMapping(value = "/{accountId}/", method = RequestMethod.DELETE, produces="application/json")
    public Account deleteAccountById(@PathVariable("accountId") String accountId) {
        return accountService.delete(accountId);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public Account createAccount(@RequestBody Account account) {
        return accountService.store(account);
    }
}
