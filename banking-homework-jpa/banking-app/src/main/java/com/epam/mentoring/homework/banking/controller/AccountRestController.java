package com.epam.mentoring.homework.banking.controller;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.service.IAccountService;
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

    /**
     * Gets all accounts.
     * @return list with accounts.
     */
    @RequestMapping(value = {"/all", "/all/"}, method = RequestMethod.GET)
    public List<Account> getAll() {
        return accountService.readAll();
    }

    /**
     * Updates account.
     * @param account account to update.
     * @return updated {@link Account} instance.
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.update(account);
    }

    /**
     * Gets account by id.
     * @param accountId account id.
     * @return {@link Account} instance.
     */
    @RequestMapping(value = "/{accountId}/", method = RequestMethod.GET, produces="application/json")
    public Account getAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.read(accountId);
    }

    /**
     * Deletes account by id.
     * @param accountId account id.
     * @return removed {@link Account} instance.
     */
    @RequestMapping(value = "/{accountId}/", method = RequestMethod.DELETE, produces="application/json")
    public Account deleteAccountById(@PathVariable("accountId") Long accountId) {
        return accountService.delete(accountId);
    }

    /**
     * Creates new account.
     * @param account account.
     * @return created {@link Account} instance.
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, produces="application/json", consumes="application/json")
    public Account createAccount(@RequestBody Account account) {
        return accountService.store(account);
    }
}
