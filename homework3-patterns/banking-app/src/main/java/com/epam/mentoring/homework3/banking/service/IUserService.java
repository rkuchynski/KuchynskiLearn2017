package com.epam.mentoring.homework3.banking.service;

import com.epam.mentoring.homework3.banking.domain.User;

import java.util.List;

/**
 * User service interface.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
public interface IUserService {
    User read(String s);

    User store(User data);

    User update(User data);

    User delete(String s);

    List<User> readAll();
}
