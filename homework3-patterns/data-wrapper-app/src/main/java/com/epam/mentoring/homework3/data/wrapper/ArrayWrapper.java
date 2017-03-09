package com.epam.mentoring.homework3.data.wrapper;

import org.apache.commons.lang3.Validate;

import java.util.Arrays;

/**
 * Array wrapper object.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class ArrayWrapper<T> {

    private Object[] array;

    public ArrayWrapper(int size) {
        array = new Object[size];
    }

    public ArrayWrapper(T[] array) {
        Validate.notNull(array);
        this.array = array.clone();
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    public void set(int index, T value) {
        array[index] = value;
    }

    public int size() {
        return array.length;
    }

    public void printToConsole() {
        System.out.println(Arrays.toString(array));
    }

}
