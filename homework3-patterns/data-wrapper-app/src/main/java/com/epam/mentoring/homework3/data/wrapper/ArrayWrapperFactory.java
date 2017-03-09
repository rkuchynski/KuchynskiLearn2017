package com.epam.mentoring.homework3.data.wrapper;

/**
 * Interface of {@link ArrayWrapper} factory.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public interface ArrayWrapperFactory<T, ST> {

    ArrayWrapper<T> createWrapper(ST source);
}
