package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Simple producer class.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class SimpleQueueProducer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleQueueProducer.class);
    private final int id;
    private final Queue<MockDataObject> sharedDataQueue;
    private long producedObjects = 0l;

    /**
     * Producer constructor.
     * @param id producer's ID.
     * @param sharedDataQueue shared queue to put data into.
     */
    public SimpleQueueProducer(int id, Queue<MockDataObject> sharedDataQueue) {
        this.id = id;
        this.sharedDataQueue = sharedDataQueue;
    }

    @Override
    public void run() {
        try {
            do {
                synchronized (sharedDataQueue) {
                    MockDataObject mockDataObject = new MockDataObject();
                    Thread.sleep(mockDataObject.getDataSize());
                    sharedDataQueue.add(mockDataObject);
                    producedObjects++;
                }
            } while (!isInterrupted());
        } catch (InterruptedException exc) {
        } finally {
            LOGGER.info("Producer #{} produced {} objects.", id, producedObjects);
        }

    }
}
