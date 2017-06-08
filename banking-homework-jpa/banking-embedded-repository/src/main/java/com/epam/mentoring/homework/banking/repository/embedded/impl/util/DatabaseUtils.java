package com.epam.mentoring.homework.banking.repository.embedded.impl.util;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database utility class.
 * <p/>
 * Date: 04/03/2017
 *
 * @author Raman Kuchynski
 */
public final class DatabaseUtils {

    public static final String BANKING_APP_SCHEMA = "BA_SCHEMA";
    public static final String BANKING_APP_USER_TABLE_SHORT = "BA_USER";
    public static final String BANKING_APP_ACCOUNT_TABLE_SHORT = "BA_ACCOUNT";
    public static final String BANKING_APP_USER_TABLE = BANKING_APP_SCHEMA + "." + BANKING_APP_USER_TABLE_SHORT;
    public static final String BANKING_APP_ACCOUNT_TABLE = BANKING_APP_SCHEMA + "." + BANKING_APP_ACCOUNT_TABLE_SHORT;

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUtils.class);

    private static final String CONSTRUCTOR_ERROR_MSG = DatabaseUtils.class.getName() +
            " is an utility class, instance creation is prohibited.";

    private DatabaseUtils() {
        throw new AssertionError(CONSTRUCTOR_ERROR_MSG);
    }

    /**
     * Close JDBC resource.
     * @param closeableResource closeable resource (connection, result set, etc).
     */
    public static void closeResource(AutoCloseable closeableResource) {
        if (null != closeableResource) {
            try {
                closeableResource.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Check if schema exists.
     * @param connection connection to DB.
     * @param schemaName schema name to check.
     * @return {@code true} if specific schema exists.
     */
    public static boolean schemaExists(Connection connection, String schemaName) {
        Preconditions.checkNotNull(connection);
        Preconditions.checkArgument(StringUtils.isNotBlank(schemaName));
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getSchemas();
            while (resultSet.next()) {
                if (schemaName.equalsIgnoreCase(resultSet.getString("TABLE_SCHEM"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get schema names from database.", e);
        }
        return false;
    }

    /**
     * Check if table exists within specified schema.
     * @param connection connection to DB.
     * @param schemaName schema name to check.
     * @param tableName table name.
     * @return {@code true} if specific table exists.
     */
    public static boolean tableExists(Connection connection, String schemaName, String tableName) {
        Preconditions.checkNotNull(connection);
        Preconditions.checkArgument(StringUtils.isNotBlank(schemaName));
        Preconditions.checkArgument(StringUtils.isNotBlank(tableName));
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, schemaName, tableName, new String[]{"TABLE"});
            while (resultSet.next()) {
                if (tableName.equalsIgnoreCase(resultSet.getString("TABLE_NAME"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Cannot get table names from database.", e);
        }
        return false;
    }

}
