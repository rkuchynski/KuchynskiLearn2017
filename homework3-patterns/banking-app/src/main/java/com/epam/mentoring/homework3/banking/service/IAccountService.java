package com.epam.mentoring.homework3.banking.service;

import com.epam.mentoring.homework3.banking.domain.Account;

import java.util.List;

/**
 * Account service interface.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
public interface IAccountService {
    Account read(String s);

    Account store(Account data);

    Account update(Account data);

    Account delete(String s);

    List<Account> readAll();
}
