package com.epam.mentoring.homework.banking.service;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.repository.embedded.api.IAccountRepository;
import com.google.common.collect.Lists;
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
    private IAccountRepository accountRepository;

    @Override
    public Account read(Integer id) {
        return accountRepository.read(id);
    }

    @Override
    public Account store(Account data) {
        return accountRepository.store(data);
    }

    @Override
    public Account update(Account data) {
        return accountRepository.update(data);
    }

    @Override
    public Account delete(Integer id) {
        return accountRepository.delete(id);
    }

    @Override
    public List<Account> readAll() {
        return Lists.newArrayList(accountRepository.getAll());
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }
}
