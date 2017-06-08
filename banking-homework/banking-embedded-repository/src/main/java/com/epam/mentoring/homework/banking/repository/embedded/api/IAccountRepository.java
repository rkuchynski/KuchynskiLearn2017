package com.epam.mentoring.homework.banking.repository.embedded.api;

import com.epam.mentoring.homework.banking.domain.Account;

import java.util.List;

/**
 * Account repository interface.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public interface IAccountRepository {

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
     * Get all accounts.
     * @return list with all {@link Account}s.
     */
    List<Account> getAll();

    /**
     * Delete all accounts.
     */
    void deleteAll();
}
