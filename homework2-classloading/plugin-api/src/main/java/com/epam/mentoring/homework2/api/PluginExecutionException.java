package com.epam.mentoring.homework2.api;

/**
 *
 * <p/>
 * Date: 02/27/2017
 *
 * @author Raman Kuchynski
 */
public class PluginExecutionException extends Exception {

    public PluginExecutionException(String message) {
        super(message);
    }

    public PluginExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public PluginExecutionException(Throwable cause) {
        super(cause);
    }

    public PluginExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
