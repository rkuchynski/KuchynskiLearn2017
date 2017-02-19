package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Application which produces OutOfMemoryError.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class MemoryConsumingApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryConsumingApplication.class);

    public static void main(String[] args) throws InterruptedException {
        LOGGER.warn("NOTE: This app will produce OutOfMemoryError and store memory dump to " +
                "\"d:\\temp\\MemoryConsumingApplication.hprof\".");
        LOGGER.warn("Press CTRL+C if you want to suspend application...");
        Thread.sleep(5000L);
        new Thread(new MemEatingThread(4, 10000)).start();
    }
}
