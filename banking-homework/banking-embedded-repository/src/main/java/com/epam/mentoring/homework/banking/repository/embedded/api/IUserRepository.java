package com.epam.mentoring.homework.banking.repository.embedded.api;

import com.epam.mentoring.homework.banking.domain.User;

import java.util.List;

/**
 * User repository interface.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public interface IUserRepository {

    User read(Integer id);

    User store(User data);

    User update(User data);

    User delete(Integer id);

    List<User> getAll();

    void deleteAll();
}
