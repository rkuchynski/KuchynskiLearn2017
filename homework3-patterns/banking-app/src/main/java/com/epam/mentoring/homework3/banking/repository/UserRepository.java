package com.epam.mentoring.homework3.banking.repository;

import com.epam.mentoring.homework3.banking.domain.User;
import org.springframework.stereotype.Component;

/**
 * Repository for {@link User}.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
@Component
public class UserRepository extends CrudMapBackedRepository<String, User> {
    @Override
    protected User doUpdateData(User storedValue, User updateValue) {
        storedValue.setName(updateValue.getName());
        return storedValue;
    }
}
