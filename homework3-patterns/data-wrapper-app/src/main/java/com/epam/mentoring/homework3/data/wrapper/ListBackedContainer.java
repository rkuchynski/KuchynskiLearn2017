package com.epam.mentoring.homework3.data.wrapper;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter from {@link List} to {@link IDataContainer}.
 * <p/>
 * Date: 03/22/2017
 *
 * @param <T> element type.
 *
 * @author Raman Kuchynski
 */
public class ListBackedContainer<T> implements IDataContainer<T> {

    private List<T> list;

    public ListBackedContainer(List<T> list) {
        this.list = list;
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void set(T value, int index) {
        if (list.size() <= index) {
            list.add(value);
        } else {
            list.set(index, value);
        }
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void print(PrintStream stream) {
        stream.println(Arrays.toString(list.toArray()));
    }
}
