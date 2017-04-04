package com.epam.mentoring.homework.banking.repository.embedded.impl.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface for helper classes which map {@link ResultSet} row to domain type.
 * <p/>
 * Date: 04/03/2017
 *
 * @param <T> domain object type.
 *
 * @author Raman Kuchynski
 */
public interface IRowMapper<T> {

    T mapResultSetRow(ResultSet resultSet) throws SQLException;

}
