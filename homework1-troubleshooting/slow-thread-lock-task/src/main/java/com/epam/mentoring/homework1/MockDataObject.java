package com.epam.mentoring.homework1;

/**
 * Mock data object which needs some time to be processed.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class MockDataObject {

    private final long dataSize;

    /**
     * Parametrized constructor.
     * @param dataSize time to work with this object in milliseconds.
     */
    public MockDataObject(long dataSize) {
        this.dataSize = dataSize;
    }

    /**
     * Default constructor. Created object requires 10 milliseconds to process it.
     */
    public MockDataObject() {
        this.dataSize = 10L;
    }

    public long getDataSize() {
        return dataSize;
    }

}
