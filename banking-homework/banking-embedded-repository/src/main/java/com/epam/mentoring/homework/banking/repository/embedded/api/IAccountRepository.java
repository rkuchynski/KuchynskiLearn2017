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

    Account read(Integer id);

    Account store(Account data);

    Account update(Account data);

    Account delete(Integer id);

    List<Account> getAll();

    void deleteAll();
}
