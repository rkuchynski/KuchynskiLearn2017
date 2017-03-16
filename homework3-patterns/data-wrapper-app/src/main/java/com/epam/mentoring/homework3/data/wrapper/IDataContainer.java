package com.epam.mentoring.homework3.data.wrapper;

import java.io.PrintStream;

/**
 * Data container interface.
 * <p/>
 * Date: 03/22/2017
 *
 * @author Raman Kuchynski
 */
public interface IDataContainer<T> {

    /**
     * Gets element from the specified index.
     *
     * @param index element index.
     * @return element value.
     */
    T get(int index);

    /**
     * Sets element at the specified index.
     *
     * @param value value to set.
     * @param index element index.
     */
    void set(T value, int index);

    /**
     * Gets container size.
     *
     * @return size.
     */
    int getSize();

    /**
     * Prints data to the following stream.
     *
     * @param stream stream to print to.
     */
    void print(PrintStream stream);

}
