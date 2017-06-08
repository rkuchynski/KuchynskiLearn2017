package com.epam.mentoring.homework.banking.repository.embedded.impl.mapper;

import com.epam.mentoring.homework.banking.domain.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Helper class to map {@link ResultSet} to {@link Account}.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public class AccountRowMapper implements IRowMapper<Account> {
    @Override
    public Account mapResultSetRow(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setAccountId(resultSet.getInt("account_id"));
        account.setUserId(resultSet.getInt("user_id"));
        account.setAmount(resultSet.getDouble("amount"));
        return account;
    }
}
