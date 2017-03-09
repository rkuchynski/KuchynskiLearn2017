package com.epam.mentoring.homework3.data.wrapper;

import java.util.List;

/**
 * Implementation of {@link ArrayWrapperFactory} to create wrappers from lists.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class ListArrayWrapperFactory<T> implements ArrayWrapperFactory<T, List<T>> {
    @SuppressWarnings("unchecked")
    @Override
    public ArrayWrapper<T> createWrapper(List<T> source) {
        return new ArrayWrapper<>((T[]) source.toArray());
    }
}
