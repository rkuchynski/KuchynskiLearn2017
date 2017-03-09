package com.epam.mentoring.homework2.web.exception;

/**
 * Exception for plugin management app.
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public class PluginManagementException extends RuntimeException {

    public PluginManagementException(String message) {
        super(message);
    }

    public PluginManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginManagementException(Throwable cause) {
        super(cause);
    }

    public PluginManagementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
