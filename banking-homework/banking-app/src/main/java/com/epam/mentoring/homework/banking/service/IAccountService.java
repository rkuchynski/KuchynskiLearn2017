package com.epam.mentoring.homework.banking.service;

import com.epam.mentoring.homework.banking.domain.Account;

import java.util.List;

/**
 * Account service interface.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
public interface IAccountService {

    /**
     * Read account by id.
     * @param id account id.
     * @return {@link Account} instance.
     */
    Account read(Integer id);

    /**
     * Store new account.
     * @param data account value.
     * @return {@link Account} instance.
     */
    Account store(Account data);

    /**
     * Update account.
     * @param data account.
     * @return {@link Account} instance.
     */
    Account update(Account data);

    /**
     * Delete account by id.
     * @param id account id.
     * @return {@link Account} instance.
     */
    Account delete(Integer id);

    /**
     * Read all accounts.
     * @return list with all {@link Account}s.
     */
    List<Account> readAll();

    /**
     * Delete all accounts.
     */
    void deleteAll();
}
