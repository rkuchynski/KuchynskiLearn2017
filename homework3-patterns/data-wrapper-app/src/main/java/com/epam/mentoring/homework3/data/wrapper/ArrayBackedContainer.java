package com.epam.mentoring.homework3.data.wrapper;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * Array wrapper object.
 * <p/>
 * Date: 09.03.2017
 *
 * @param <T> value type.
 *
 * @author Raman Kuchynski
 */
public class ArrayBackedContainer<T> implements IDataContainer<T> {

    private Object[] array;

    public ArrayBackedContainer(int size) {
        array = new Object[size];
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        array[index] = value;
    }

    @Override
    public int getSize() {
        return array.length;
    }

    @Override
    public void print(PrintStream stream) {
        stream.println(Arrays.toString(array));
    }
}
