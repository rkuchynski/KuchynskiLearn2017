package com.epam.mentoring.homework1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread implementation that acquires two resources from {@link SharedResourceHolder}.
 * <p/>
 * Date: 02/18/2017
 *
 * @author Raman Kuchynski
 */
public class TwoResourceThread extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwoResourceThread.class);

    private final int firstRequiredResource;
    private final SharedResourceHolder holderInstance;

    /**
     * Constructor.
     * @param firstRequiredResource thread number and number of the first resource to get.
     * @param resourceHolder shared resource holder.
     */
    public TwoResourceThread(int firstRequiredResource, SharedResourceHolder resourceHolder) {
        this.firstRequiredResource = firstRequiredResource;
        this.holderInstance = resourceHolder;
    }

    @Override
    public void run() {
        LOGGER.info("Thread #{} started.", firstRequiredResource);
        LOGGER.info("Thread #{}: acquiring resource #{}...", firstRequiredResource, firstRequiredResource);
        Object resource1 = holderInstance.getSharedResource(firstRequiredResource);
        synchronized (resource1) {
            LOGGER.info("Thread #{}: resource #{} acquired.", firstRequiredResource, firstRequiredResource);
            LOGGER.info("Thread #{}: working with resource #{}({})...", firstRequiredResource, firstRequiredResource,
                    resource1.hashCode());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exc) {
                LOGGER.error("Error with thread #" + firstRequiredResource + ": ", exc);
            }
            int nextResourceNumber = holderInstance.getNextResourceNumber(firstRequiredResource);
            Object resource2 = holderInstance.getSharedResource(nextResourceNumber);
            LOGGER.info("Thread #{}: acquiring resource #{}...", firstRequiredResource, nextResourceNumber);
            synchronized (resource2) {
                LOGGER.info("Thread #{}: working with resource #{}({})...", firstRequiredResource, nextResourceNumber,
                        resource2.hashCode());
                LOGGER.info("Thread #{}: finished.", firstRequiredResource);
            }
        }
    }
}
