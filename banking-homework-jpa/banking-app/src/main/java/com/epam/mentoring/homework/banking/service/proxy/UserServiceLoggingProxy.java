package com.epam.mentoring.homework.banking.service.proxy;

import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proxy for {@link IUserService} that is responsible for logging.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
@Service("userServiceLoggingProxy")
public class UserServiceLoggingProxy implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceLoggingProxy.class);

    private static final String SERVICE_PREFIX = "[UserServiceLoggingProxy]: ";

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
    private static final String AUDIT_DELETE_SUCCESS_MSG = SERVICE_PREFIX + "User {} removed from storage.";

    private static final String AUDIT_DELETE_ALL_MSG = SERVICE_PREFIX + "Deleting all users...";
    private static final String AUDIT_DELETE_ALL_SUCCESS_MSG = SERVICE_PREFIX + "All users removed.";

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Override
    public User read(Long id) {
        LOGGER.info(AUDIT_READ_MSG, id);
        User user = userService.read(id);
        if (null == user) {
            LOGGER.info(AUDIT_READ_NULL_MSG, id);
        } else {
            LOGGER.info(AUDIT_READ_SUCCESS_MSG, id, user);
        }
        return user;
    }

    @Override
    public User store(User data) {
        LOGGER.info(AUDIT_STORE_MSG, data);
        User result = userService.store(data);
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
        User result = userService.update(data);
        if (null == result) {
            LOGGER.info(AUDIT_UPDATE_ERROR_MSG, data);
        } else {
            LOGGER.info(AUDIT_UPDATE_SUCCESS_MSG, data);
        }
        return result;
    }

    @Override
    public User delete(Long id) {
        LOGGER.info(AUDIT_DELETE_MSG, id);
        User result = userService.delete(id);
        if (null == result) {
            LOGGER.info(AUDIT_READ_NULL_MSG, id);
        } else {
            LOGGER.info(AUDIT_DELETE_SUCCESS_MSG, result);
        }
        return result;
    }

    @Override
    public List<User> readAll() {
        return userService.readAll();
    }

    @Override
    public void deleteAll() {
        LOGGER.info(AUDIT_DELETE_ALL_MSG);
        userService.deleteAll();
        LOGGER.info(AUDIT_DELETE_ALL_SUCCESS_MSG);
    }
}
