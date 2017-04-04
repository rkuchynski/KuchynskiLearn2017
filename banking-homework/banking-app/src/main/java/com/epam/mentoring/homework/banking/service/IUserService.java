package com.epam.mentoring.homework.banking.service;

import com.epam.mentoring.homework.banking.domain.User;

import java.util.List;

/**
 * User service interface.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
public interface IUserService {
    User read(Integer id);

    User store(User data);

    User update(User data);

    User delete(Integer id);

    List<User> readAll();

    void deleteAll();
}
