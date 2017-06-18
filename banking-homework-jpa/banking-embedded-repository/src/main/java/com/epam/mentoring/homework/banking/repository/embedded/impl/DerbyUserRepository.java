package com.epam.mentoring.homework.banking.repository.embedded.impl;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.repository.embedded.api.IUserRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation of {@link IUserRepository} for Derby Embedded DB.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
@Component
public class DerbyUserRepository implements IUserRepository {

    @Autowired
    private CrudRepository crudRepository;

    @Override
    public User read(Long id) {
        return crudRepository.read(User.class, id);
    }

    @Override
    public User store(User data) {
        return crudRepository.store(User.class, data);
    }

    @Override
    public User update(User data) {
        return crudRepository.update(data);
    }

    @Override
    public User delete(Long id) {
        User userToDelete = crudRepository.read(User.class, id);
        if (CollectionUtils.isNotEmpty(userToDelete.getAccounts())) {
            for(Account nestedAccount : userToDelete.getAccounts()) {
                crudRepository.delete(Account.class, nestedAccount.getAccountId());
            }
        }
        crudRepository.delete(User.class, id);
        return userToDelete;
    }

    @Override
    public List<User> getAll() {
        return crudRepository.getAll(User.class);
    }

    @Override
    public void deleteAll() {
        crudRepository.deleteAll(User.class);
    }
}
