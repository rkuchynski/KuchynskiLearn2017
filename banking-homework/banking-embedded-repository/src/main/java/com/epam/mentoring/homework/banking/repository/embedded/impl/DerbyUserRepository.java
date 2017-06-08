package com.epam.mentoring.homework.banking.repository.embedded.impl;

import static com.epam.mentoring.homework.banking.repository.embedded.impl.util.DatabaseUtils.BANKING_APP_USER_TABLE;
import static com.epam.mentoring.homework.banking.repository.embedded.impl.util.DatabaseUtils.closeResource;

import com.epam.mentoring.homework.banking.domain.User;
import com.epam.mentoring.homework.banking.repository.embedded.api.IUserRepository;
import com.epam.mentoring.homework.banking.repository.embedded.impl.mapper.UserRowMapper;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Implementation of {@link IUserRepository} for Derby Embedded DB.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
@Component
public class DerbyUserRepository implements IUserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(DerbyUserRepository.class);

    private static final String SELECT_USER_BY_ID_SQL = "select user_id, user_name from " + BANKING_APP_USER_TABLE +
            " where user_id = ?";
    private static final String INSERT_USER_SQL = "insert into " + BANKING_APP_USER_TABLE +
            "(user_name) values(?)";
    private static final String UPDATE_USER_SQL = "update " + BANKING_APP_USER_TABLE +
            " set user_name=? where user_id=?";
    private static final String DELETE_USER_SQL = "delete from " + BANKING_APP_USER_TABLE +
            " where user_id=?";
    private static final String SELECT_ALL_USERS_SQL = "select user_id, user_name from " + BANKING_APP_USER_TABLE + "";
    private static final String DELETE_ALL_USERS_SQL = "delete from " + BANKING_APP_USER_TABLE;

    private static final int INDEX_ID = 1;
    private static final int USER_ID = 2;

    private UserRowMapper rowMapper = new UserRowMapper();

    @Autowired
    private DataSource dataSource;

    @Override
    public User read(Integer id) {

        ResultSet resultSet = null;
        User user = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {

            statement.setInt(INDEX_ID, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = rowMapper.mapResultSetRow(resultSet);
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to get user by id=" + id, exc);
        } finally {
            closeResource(resultSet);
        }

        return user;
    }

    @Override
    public User store(User data) {
        ResultSet resultSet = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement
                    = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(INDEX_ID, data.getName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                data.setUserId(resultSet.getInt(1));
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to insert user=" + data.toString(), exc);
        } finally {
            closeResource(resultSet);
        }

        return data;
    }

    @Override
    public User update(User data) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {

            statement.setString(INDEX_ID, data.getName());
            statement.setInt(USER_ID, data.getUserId());
            statement.executeUpdate();

        } catch (SQLException exc) {
            LOGGER.info("Failed to update user=" + data.toString(), exc);
            return null;
        }
        return data;
    }

    @Override
    public User delete(Integer id) {
        ResultSet resultSet = null;
        User user = null;
        boolean successfulDelete = false;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL);
            PreparedStatement deleteStatement = connection.prepareStatement(DELETE_USER_SQL)) {

            selectStatement.setInt(INDEX_ID, id);
            resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                user = rowMapper.mapResultSetRow(resultSet);
            }
            deleteStatement.setInt(INDEX_ID, id);
            successfulDelete = deleteStatement.executeUpdate() > 0;

        } catch (SQLException exc) {
            LOGGER.info("Failed to delete user by id=" + id, exc);
        } finally {
            closeResource(resultSet);
        }

        return successfulDelete ? user : null;
    }

    @Override
    public List<User> getAll() {
        ResultSet resultSet = null;
        List<User> users = Lists.newArrayList();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(rowMapper.mapResultSetRow(resultSet));
            }

        } catch (SQLException exc) {
            LOGGER.info("Failed to get users ", exc);
        } finally {
            closeResource(resultSet);
        }

        return users;
    }

    @Override
    public void deleteAll() {
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(DELETE_ALL_USERS_SQL);
        } catch (SQLException exc) {
            LOGGER.info("Failed to delete users ", exc);
        }
    }
}
