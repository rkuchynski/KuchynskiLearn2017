package com.epam.mentoring.homework3.banking.service;

import java.util.List;

/**
 * CRUD service interface.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public interface ICrudService<ID, T> {

    List<T> readAll();

    T read(ID id);

    T store(T data);

    T update(T data);

    T delete(ID id);

}
