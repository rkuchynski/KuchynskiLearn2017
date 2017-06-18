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

    /**
     * Read user by id.
     * @param id user id.
     * @return {@link User} instance.
     */
    User read(Long id);

    /**
     * Store new user.
     * @param data user value.
     * @return {@link User} instance.
     */
    User store(User data);

    /**
     * Update user.
     * @param data user.
     * @return {@link User} instance.
     */
    User update(User data);

    /**
     * Deletes user by id.
     * @param id user id.
     * @return {@link User} instance.
     */
    User delete(Long id);

    /**
     * Read all users.
     * @return list of all {@link User}s.
     */
    List<User> readAll();

    /**
     * Delete all users.
     */
    void deleteAll();
}
