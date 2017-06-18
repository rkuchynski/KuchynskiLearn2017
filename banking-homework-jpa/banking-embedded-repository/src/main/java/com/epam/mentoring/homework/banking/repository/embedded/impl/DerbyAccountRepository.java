package com.epam.mentoring.homework.banking.repository.embedded.impl;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.repository.embedded.api.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link IAccountRepository} for Derby Embedded DB.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
@Component
public class DerbyAccountRepository implements IAccountRepository {

    @Autowired
    private CrudRepository crudRepository;

    @Override
    public Account read(Long id) {
        return crudRepository.read(Account.class, id);
    }

    @Override
    public Account store(Account data) {
        return crudRepository.store(Account.class, data);
    }

    @Override
    public Account update(Account data) {
        return crudRepository.update(data);
    }

    @Override
    public Account delete(Long id) {
        return crudRepository.delete(Account.class, id);
    }

    @Override
    public List<Account> getAll() {
        return crudRepository.getAll(Account.class);
    }

    @Override
    public void deleteAll() {
        crudRepository.deleteAll(Account.class);
    }
}
