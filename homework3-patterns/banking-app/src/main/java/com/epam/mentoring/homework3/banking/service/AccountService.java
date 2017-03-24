package com.epam.mentoring.homework3.banking.service;

import com.epam.mentoring.homework3.banking.domain.Account;
import com.epam.mentoring.homework3.banking.repository.AccountRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Account service.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Service("accountService")
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account read(String s) {
        return accountRepository.read(s);
    }

    @Override
    public Account store(Account data) {
        return accountRepository.store(data.getAccountId(), data);
    }

    @Override
    public Account update(Account data) {
        return accountRepository.update(data.getAccountId(), data);
    }

    @Override
    public Account delete(String s) {
        return accountRepository.delete(s);
    }

    @Override
    public List<Account> readAll() {
        return Lists.newArrayList(accountRepository.readAll());
    }
}
