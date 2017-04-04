package com.epam.mentoring.homework.banking.repository.embedded.impl.mapper;

import com.epam.mentoring.homework.banking.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper class to map {@link ResultSet} to {@link User}.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public class UserRowMapper implements IRowMapper<User> {
    @Override
    public User mapResultSetRow(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("user_name"));
        return user;
    }
}
