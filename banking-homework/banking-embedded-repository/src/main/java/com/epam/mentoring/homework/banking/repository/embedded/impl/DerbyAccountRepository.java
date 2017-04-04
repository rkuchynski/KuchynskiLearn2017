package com.epam.mentoring.homework.banking.repository.embedded.impl;

import com.epam.mentoring.homework.banking.domain.Account;
import com.epam.mentoring.homework.banking.repository.embedded.api.IAccountRepository;
import com.epam.mentoring.homework.banking.repository.embedded.impl.mapper.AccountRowMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

import static com.epam.mentoring.homework.banking.repository.embedded.impl.util.DatabaseUtils.BANKING_APP_ACCOUNT_TABLE;
import static com.epam.mentoring.homework.banking.repository.embedded.impl.util.DatabaseUtils.closeResource;

/**
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
@Component
public class DerbyAccountRepository implements IAccountRepository {

    private static final String SELECT_ACCOUNT_BY_ID_SQL =
            "select account_id, user_id, amount from " + BANKING_APP_ACCOUNT_TABLE + " where account_id = ?";
    private static final String INSERT_ACCOUNT_SQL = "insert into " + BANKING_APP_ACCOUNT_TABLE +
            "(user_id, amount) values(?, ?)";
    private static final String UPDATE_ACCOUNT_SQL = "update " + BANKING_APP_ACCOUNT_TABLE +
            " set user_id=?, amount=? where account_id=?";
    private static final String DELETE_ACCOUNT_SQL = "delete from " + BANKING_APP_ACCOUNT_TABLE +
            " where account_id=?";
    private static final String SELECT_ALL_ACCOUNTS_SQL =
            "select account_id, user_id, amount from " + BANKING_APP_ACCOUNT_TABLE;
    private static final String DELETE_ALL_ACCOUNTS_SQL =
            "delete from " + BANKING_APP_ACCOUNT_TABLE;

    private static final Logger LOGGER = LoggerFactory.getLogger(DerbyAccountRepository.class);

    private AccountRowMapper rowMapper = new AccountRowMapper();

    @Autowired
    private DataSource dataSource;

    @Override
    public Account read(Integer id) {
        ResultSet resultSet = null;
        Account account = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID_SQL)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                account = rowMapper.mapResultSetRow(resultSet);
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to get account by id=" + id, exc);
        } finally {
            closeResource(resultSet);
        }

        return account;
    }

    @Override
    public Account store(Account data) {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(INSERT_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, data.getUserId());
            statement.setDouble(2, data.getAmount());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                data.setAccountId(resultSet.getInt(1));
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to insert account=" + data.toString(), exc);
        } finally {
            closeResource(resultSet);
        }

        return data;
    }

    @Override
    public Account update(Account data) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_ACCOUNT_SQL)) {

            statement.setInt(1, data.getUserId());
            statement.setDouble(2, data.getAmount());
            statement.setInt(3, data.getAccountId());
            statement.executeUpdate();

        } catch (SQLException exc) {
            LOGGER.info("Failed to update account=" + data.toString(), exc);
            return null;
        }
        return data;
    }

    @Override
    public Account delete(Integer id) {
        ResultSet resultSet = null;
        Account account = null;
        boolean successfulDelete = false;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID_SQL);
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_ACCOUNT_SQL)) {

            selectStatement.setInt(1, id);
            resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                account = rowMapper.mapResultSetRow(resultSet);
            }
            deleteStatement.setInt(1, id);
            successfulDelete = deleteStatement.executeUpdate() > 0;

        } catch (SQLException exc) {
            LOGGER.info("Failed to delete account by id=" + id, exc);
        } finally {
            closeResource(resultSet);
        }

        return successfulDelete ? account : null;
    }

    @Override
    public List<Account> getAll() {
        ResultSet resultSet = null;
        List<Account> accounts = Lists.newArrayList();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ACCOUNTS_SQL)) {

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                accounts.add(rowMapper.mapResultSetRow(resultSet));
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to get accounts ", exc);
        } finally {
            closeResource(resultSet);
        }

        return accounts;
    }

    @Override
    public void deleteAll() {
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL_ACCOUNTS_SQL);
        } catch (SQLException exc) {
            LOGGER.info("Failed to delete accounts ", exc);
        }
    }
}
