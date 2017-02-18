package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Java application that produces deadlock and collects thread dumps.
 * Add parameter <b>-Ddeadlock.dump.path</b> to customize where to store the thread dumps.
 * <p/>
 * Date: 02/18/2017
 *
 * @author Raman Kuchynski
 */
public class DeadlockApplication {

    private static final int THREAD_COUNT = 4;
    private static final Logger LOGGER = LoggerFactory.getLogger(DeadlockApplication.class);
    private static final String DEFAULT_DUMP_PATH = "D:\\temp\\DeadlockApplication.dump.log";
    private static final String DUMP_PATH_PARAM = "deadlock.dump.path";

    public static void main(String[] args) throws InterruptedException, IOException {
        SharedResourceHolder sharedResourceHolder = new SharedResourceHolder(THREAD_COUNT);
        String dumpFilePath = System.getProperty(DUMP_PATH_PARAM, DEFAULT_DUMP_PATH);

        for(int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new TwoResourceThread(i, sharedResourceHolder);
            thread.start();
        }
        LOGGER.info("Waiting for deadlock...");
        Thread.sleep(2500);
        LOGGER.info("Saving thread dump...");
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File(dumpFilePath)));
        for(Thread t : Thread.getAllStackTraces().keySet()) {
            fileWriter.write(generateThreadDump(t));
        }
        fileWriter.close();
        LOGGER.info("Thread dump successfully stored to {}", dumpFilePath);
    }

    private static String generateThreadDump(Thread thread) {
        StringBuilder dumpBuilder = new StringBuilder();
        dumpBuilder.append("Thread \"").append(thread.getName()).append("\" (id=").append(thread.getId()).append("):");
        dumpBuilder.append("\n\tjava.lang.Thread.State: ").append(thread.getState());
        final StackTraceElement[] stackTraceElements = thread.getStackTrace();
        for (final StackTraceElement stackTraceElement : stackTraceElements) {
            dumpBuilder.append("\n\t\tat ").append(stackTraceElement);
        }
        dumpBuilder.append("\n\n");
        return dumpBuilder.toString();
    }

}
