package com.epam.mentoring.homework.banking.service.proxy;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Proxy for {@link IAccountService} that is responsible for logging.
 * <p/>
 * Date: 03/24/2017
 *
 * @author Raman Kuchynski
 */
@Service("accountServiceLoggingProxy")
public class AccountServiceLoggingProxy implements IAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceLoggingProxy.class);

    private static final String SERVICE_PREFIX = "[AccountServiceLoggingProxy]: ";

    private static final String AUDIT_READ_MSG = SERVICE_PREFIX + "Attempting to read account with ID {}.";
    private static final String AUDIT_READ_NULL_MSG = SERVICE_PREFIX + "Account with id {} does not exist.";
    private static final String AUDIT_READ_SUCCESS_MSG = SERVICE_PREFIX + "Account with ID {} found: {}.";

    private static final String AUDIT_STORE_MSG = SERVICE_PREFIX + "Storing new account {}.";
    private static final String AUDIT_STORE_NEW_MSG = SERVICE_PREFIX + "Account {} stored successfully.";
    private static final String AUDIT_STORE_ERROR_MSG = SERVICE_PREFIX + "Account with id {} already exist.";

    private static final String AUDIT_UPDATE_MSG =
            SERVICE_PREFIX + "Attempting to update account: id={}, new user name: {}, new money amount: {}.";
    private static final String AUDIT_UPDATE_SUCCESS_MSG = SERVICE_PREFIX + "Account {} updated successfully.";
    private static final String AUDIT_UPDATE_ERROR_MSG = SERVICE_PREFIX + "Account {} does not exist, cannot update.";

    private static final String AUDIT_DELETE_MSG = SERVICE_PREFIX + "Deleting account with id {}.";
    private static final String AUDIT_DELETE_SUCCESS_MSG = SERVICE_PREFIX + "Account {} removed from storage.";

    private static final String AUDIT_DELETE_ALL_MSG = SERVICE_PREFIX + "Deleting all accounts...";
    private static final String AUDIT_DELETE_ALL_SUCCESS_MSG = SERVICE_PREFIX + "All accounts removed.";

    @Autowired
    @Qualifier("accountService")
    private IAccountService accountService;

    @Override
    public Account read(Integer id) {
        LOGGER.info(AUDIT_READ_MSG, id);
        Account account = accountService.read(id);
        if (null == account) {
            LOGGER.info(AUDIT_READ_NULL_MSG, id);
        } else {
            LOGGER.info(AUDIT_READ_SUCCESS_MSG, id, account);
        }
        return account;
    }

    @Override
    public Account store(Account data) {
        LOGGER.info(AUDIT_STORE_MSG, data);
        Account result = accountService.store(data);
        if (null == result) {
            LOGGER.info(AUDIT_STORE_ERROR_MSG, data.getAccountId());
        } else {
            LOGGER.info(AUDIT_STORE_NEW_MSG, result);
        }
        return result;
    }

    @Override
    public Account update(Account data) {
        LOGGER.info(AUDIT_UPDATE_MSG, data.getAccountId(), data.getUserId(), data.getAmount());
        Account result = accountService.update(data);
        if (null == result) {
            LOGGER.info(AUDIT_UPDATE_ERROR_MSG, data);
        } else {
            LOGGER.info(AUDIT_UPDATE_SUCCESS_MSG, data);
        }
        return result;
    }

    @Override
    public Account delete(Integer id) {
        LOGGER.info(AUDIT_DELETE_MSG, id);
        Account result = accountService.delete(id);
        if (null == result) {
            LOGGER.info(AUDIT_READ_NULL_MSG, id);
        } else {
            LOGGER.info(AUDIT_DELETE_SUCCESS_MSG, result);
        }
        return result;
    }

    @Override
    public List<Account> readAll() {
        return accountService.readAll();
    }

    @Override
    public void deleteAll() {
        LOGGER.info(AUDIT_DELETE_ALL_MSG);
        accountService.deleteAll();
        LOGGER.info(AUDIT_DELETE_ALL_SUCCESS_MSG);
    }
}
