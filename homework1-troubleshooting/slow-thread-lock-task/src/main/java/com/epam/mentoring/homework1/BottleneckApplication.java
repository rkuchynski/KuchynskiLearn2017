package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Java application that reproduce bottleneck with multiple data producers and single data consumer.
 * Add parameter <b>-Dbottleneck.dump.path</b> to customize where to store the thread dumps.
 * Add parameter <b>-Dbottleneck.dump.time</b> to customize time in seconds before collection of thread dumps.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class BottleneckApplication {

    private static final int PRODUCER_COUNT = 4;
    private static final Logger LOGGER = LoggerFactory.getLogger(BottleneckApplication.class);
    private static final String DEFAULT_DUMP_PATH = "D:\\temp\\BottleneckApplication.dump.log";
    private static final long DEFAULT_DUMP_TIME = 30000L;
    private static final String DUMP_PATH_PARAM = "bottleneck.dump.path";
    private static final String DUMP_TIME_PARAM = "bottleneck.dump.time";

    public static void main(String[] args) throws InterruptedException, IOException {
        String dumpFilePath = System.getProperty(DUMP_PATH_PARAM, DEFAULT_DUMP_PATH);
        long dumpTime = getDumpTimeInMilliseconds();

        LOGGER.info("Add parameter -Dbottleneck.dump.path to customize where to store the thread dumps.");
        LOGGER.info("Add parameter -Dbottleneck.dump.time to customize time in seconds " +
                "before collection of thread dumps.");

        List<Thread> threads = new ArrayList<>();
        Queue<MockDataObject> sharedQueue = new ArrayDeque<>();
        SimpleQueueConsumer consumer = new SimpleQueueConsumer(sharedQueue);
        threads.add(consumer);
        consumer.start();

        for(int i = 0; i < PRODUCER_COUNT; i++) {
            SimpleQueueProducer producer = new SimpleQueueProducer(i, sharedQueue);
            threads.add(producer);
            producer.start();
        }

        LOGGER.info("Waiting for bottleneck...");
        Thread.sleep(dumpTime);
        LOGGER.info("Saving thread dump...");

        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File(dumpFilePath)));
        for(Thread t : Thread.getAllStackTraces().keySet()) {
            fileWriter.write(generateThreadDump(t));
        }
        fileWriter.close();
        LOGGER.info("Thread dump successfully stored to {}", dumpFilePath);

        LOGGER.info("Stopping threads...", dumpFilePath);
        for(Thread t : threads) {
            t.interrupt();
        }
        for(Thread t : threads) {
            t.join();
        }
        LOGGER.info("Threads successfully stopped.", dumpFilePath);
    }

    private static long getDumpTimeInMilliseconds() {
        String dumpTimeRaw = System.getProperty(DUMP_TIME_PARAM, "0");
        long dumpTime = 0L;
        try {
            dumpTime = Long.parseLong(dumpTimeRaw);
        } catch (NumberFormatException e) {
        }
        return (dumpTime > 0L) ? dumpTime * 1000L : DEFAULT_DUMP_TIME;
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
