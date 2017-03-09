package com.epam.mentoring.homework2.web.util;

import java.text.MessageFormat;

/**
 * Utility class to format messages.
 * <p/>
 * Date: 08.03.2017
 *
 * @author Raman Kuchynski
 */
public final class MessageFormatUtil {

    private static final String CONSTRUCTOR_ERROR_MSG =
            MessageFormatUtil.class.getName() + " is an utility class, instantiation is prohibited.";

    private MessageFormatUtil() {
        throw new AssertionError(CONSTRUCTOR_ERROR_MSG);
    }

    public static String formatMessage(String pattern, Object ... args) {
        return new MessageFormat(pattern).format(args);
    }
}
