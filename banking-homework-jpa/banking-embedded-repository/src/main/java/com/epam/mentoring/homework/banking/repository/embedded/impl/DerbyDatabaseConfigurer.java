package com.epam.mentoring.homework.banking.repository.embedded.impl;

import com.epam.mentoring.homework.banking.repository.embedded.impl.util.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Bean to initialize embedded DB.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public class DerbyDatabaseConfigurer implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DerbyDatabaseConfigurer.class);

    private static final String CREATE_SCHEMA_SQL =
            new StringBuilder().append("CREATE SCHEMA ")
            .append(DatabaseUtils.BANKING_APP_SCHEMA).toString();

    private static final String CREATE_USER_TABLE_SQL =
            new StringBuilder().append("CREATE TABLE ").append(DatabaseUtils.BANKING_APP_USER_TABLE)
            .append("(user_id integer primary key GENERATED ALWAYS AS IDENTITY ")
            .append("(START WITH 1, INCREMENT BY 1), user_name varchar(100) not null)").toString();

    private static final String CREATE_ACCOUNT_TABLE =
            new StringBuilder().append("CREATE TABLE ").append(DatabaseUtils.BANKING_APP_ACCOUNT_TABLE)
            .append("(account_id integer primary key GENERATED ALWAYS AS IDENTITY ")
            .append("(START WITH 1, INCREMENT BY 1), user_id integer not null, amount double not null,")
            .append("CONSTRAINT fk_account_2_user FOREIGN KEY (user_id) REFERENCES ")
            .append(DatabaseUtils.BANKING_APP_USER_TABLE)
            .append("(user_id))").toString();

    private String dataSourceBean;

    public void setDataSourceBean(String value) {
        this.dataSourceBean = value;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!dataSourceBean.equals(beanName)) {
            return bean;
        }
        LOGGER.info("Data source bean \"{}\" found: {}", beanName, bean);

        DataSource dataSource = (DataSource) bean;
        Connection connection = null;
        Statement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            connection.setAutoCommit(false);

            if (!DatabaseUtils.schemaExists(connection, DatabaseUtils.BANKING_APP_SCHEMA)) {
                LOGGER.info("{} schema not found, creating...", DatabaseUtils.BANKING_APP_SCHEMA);
                statement.execute(CREATE_SCHEMA_SQL);
                LOGGER.info("{} schema created.", DatabaseUtils.BANKING_APP_SCHEMA);
            }
            if (!DatabaseUtils.tableExists(connection, DatabaseUtils.BANKING_APP_SCHEMA,
                    DatabaseUtils.BANKING_APP_USER_TABLE_SHORT)) {
                LOGGER.info("{} table not found, creating...", DatabaseUtils.BANKING_APP_USER_TABLE);
                statement.execute(CREATE_USER_TABLE_SQL);
                LOGGER.info("{} table created.", DatabaseUtils.BANKING_APP_USER_TABLE);
            }
            if (!DatabaseUtils.tableExists(connection, DatabaseUtils.BANKING_APP_SCHEMA,
                    DatabaseUtils.BANKING_APP_ACCOUNT_TABLE_SHORT)) {
                LOGGER.info("{} table not found, creating...", DatabaseUtils.BANKING_APP_ACCOUNT_TABLE);
                statement.execute(CREATE_ACCOUNT_TABLE);
                LOGGER.info("{} table created.", DatabaseUtils.BANKING_APP_ACCOUNT_TABLE);
            }

            connection.commit();

        } catch (SQLException exc) {
            LOGGER.error("Failed to initialize database: ", exc);
            if (null != connection) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    LOGGER.error("Failed to rollback transaction: ", e);
                }
            }
        } finally {
            DatabaseUtils.closeResource(statement);
            DatabaseUtils.closeResource(connection);
        }

        LOGGER.info("Data source bean \"{}\" successfullty configured.", beanName);

        return bean;
    }
}
