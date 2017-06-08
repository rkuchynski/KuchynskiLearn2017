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

    /**
     * Read user by id.
     * @param id user id.
     * @return {@link User} instance.
     */
    User read(Integer id);

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
    User delete(Integer id);

    /**
     * Get all users.
     * @return list of all {@link User}s.
     */
    List<User> getAll();

    /**
     * Delete all users.
     */
    void deleteAll();
}
