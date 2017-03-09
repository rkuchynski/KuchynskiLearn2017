package com.epam.mentoring.homework3.banking.repository;

import com.epam.mentoring.homework3.banking.domain.Account;
import org.springframework.stereotype.Component;

/**
 * Repository for {@link Account}.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Component
public class AccountRepository extends CrudMapBackedRepository<String, Account> {
    @Override
    protected Account doUpdateData(Account storedValue, Account updateValue) {
        storedValue.setAmount(updateValue.getAmount());
        storedValue.setUserId(updateValue.getUserId());
        return storedValue;
    }
}
