package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

/**
 * Simple consumer class.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class SimpleQueueConsumer extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleQueueProducer.class);
    private final Queue<MockDataObject> sharedDataQueue;
    private long consumedObjects = 0L;

    /**
     * Consumer constructor.
     * @param sharedDataQueue shared queue to get data from.
     */
    public SimpleQueueConsumer(Queue<MockDataObject> sharedDataQueue) {
        this.sharedDataQueue = sharedDataQueue;
    }

    @Override
    public void run() {
        try {
            do {
                synchronized (sharedDataQueue) {
                    MockDataObject mockDataObject = sharedDataQueue.poll();
                    if (null != mockDataObject) {
                        Thread.sleep(mockDataObject.getDataSize());
                        consumedObjects++;
                    } else {
                        Thread.sleep(50);
                    }
                }
            } while (!isInterrupted());
        } catch (InterruptedException exc) {
        } finally {
            LOGGER.info("Consumer consumed {} objects.", consumedObjects);
        }
    }
}
