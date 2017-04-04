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
    Account read(Integer id);

    Account store(Account data);

    Account update(Account data);

    Account delete(Integer id);

    List<Account> readAll();

    void deleteAll();
}
