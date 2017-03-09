package com.epam.mentoring.homework3.banking.service;

import com.epam.mentoring.homework3.banking.domain.User;
import com.epam.mentoring.homework3.banking.repository.UserRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * User service.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Service
public class UserService implements ICrudService<String, User>{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private static final String SERVICE_PREFIX = "[UserService]: ";

    private static final String AUDIT_READ_MSG = SERVICE_PREFIX + "Attempting to read user with ID {}.";
    private static final String AUDIT_READ_NULL_MSG = SERVICE_PREFIX + "User with id {} does not exist.";
    private static final String AUDIT_READ_SUCCESS_MSG = SERVICE_PREFIX + "User with ID {} found: {}.";

    private static final String AUDIT_STORE_MSG = SERVICE_PREFIX + "Storing new user {}.";
    private static final String AUDIT_STORE_NEW_MSG = SERVICE_PREFIX + "User {} stored successfully.";
    private static final String AUDIT_STORE_ERROR_MSG = SERVICE_PREFIX + "User with id {} already exist.";

    private static final String AUDIT_UPDATE_MSG =
            SERVICE_PREFIX + "Attempting to update user: id={}, new user name: {}.";
    private static final String AUDIT_UPDATE_SUCCESS_MSG = SERVICE_PREFIX + "User {} updated successfully.";
    private static final String AUDIT_UPDATE_ERROR_MSG = SERVICE_PREFIX + "User {} does not exist, cannot update.";

    private static final String AUDIT_DELETE_MSG = SERVICE_PREFIX + "Deleting user with id {}.";
    private static final String AUDIT_DELETE_NULL_MSG = SERVICE_PREFIX + "User with id {} does not exist.";
    private static final String AUDIT_DELETE_SUCCESS_MSG = SERVICE_PREFIX + "User {} removed from storage.";


    @Autowired
    private UserRepository userRepository;

    @Override
    public User read(String s) {
        LOGGER.info(AUDIT_READ_MSG, s);
        User user = userRepository.read(s);
        if (null == user) {
            LOGGER.info(AUDIT_READ_NULL_MSG, s);
        } else {
            LOGGER.info(AUDIT_READ_SUCCESS_MSG, s, user);
        }
        return user;
    }

    @Override
    public User store(User data) {
        LOGGER.info(AUDIT_STORE_MSG, data);
        User result = userRepository.store(data.getUserId(), data);
        if (null == result) {
            LOGGER.info(AUDIT_STORE_ERROR_MSG, data.getUserId());
        } else {
            LOGGER.info(AUDIT_STORE_NEW_MSG, result);
        }
        return result;
    }

    @Override
    public User update(User data) {
        LOGGER.info(AUDIT_UPDATE_MSG, data.getUserId(), data.getName());
        User result = userRepository.update(data.getUserId(), data);
        if (null == result) {
            LOGGER.info(AUDIT_UPDATE_ERROR_MSG, data);
        } else {
            LOGGER.info(AUDIT_UPDATE_SUCCESS_MSG, data);
        }
        return result;
    }

    @Override
    public User delete(String s) {
        LOGGER.info(AUDIT_DELETE_MSG, s);
        User result = userRepository.delete(s);
        if (null == result) {
            LOGGER.info(AUDIT_DELETE_NULL_MSG, s);
        } else {
            LOGGER.info(AUDIT_DELETE_SUCCESS_MSG, result);
        }
        return result;
    }

    @Override
    public List<User> readAll() {
        return Lists.newArrayList(userRepository.readAll());
    }
}
