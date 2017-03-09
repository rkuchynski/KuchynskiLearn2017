package com.epam.mentoring.homework3.banking.repository;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * CRUD repository generic class.
 * <p/>
 * Date: 09.03.2017
 *
 * @param <ID> id type.
 * @param <T> domain object type.
 *
 * @author Raman Kuchynski
 */
public abstract class CrudMapBackedRepository<ID, T> {

    private Map<ID, T> dataMap;

    public CrudMapBackedRepository() {
        dataMap = Maps.newConcurrentMap();
    }

    public Collection<T> readAll() {
        return dataMap.values();
    }

    public T read(ID id) {
        if (dataMap.containsKey(id)) {
            return dataMap.get(id);
        }
        return null;
    }

    public T store(ID id, T data) {
        if (dataMap.containsKey(id)) {
            return null;
        } else {
            dataMap.put(id, data);
            return data;
        }
    }

    public T update(ID id, T data) {
        if (dataMap.containsKey(id)) {
            T newData = doUpdateData(dataMap.get(id), data);
            dataMap.remove(id);
            dataMap.put(id, newData);
            return newData;
        } else {
            return null;
        }
    }

    public T delete(ID id) {
        if (dataMap.containsKey(id)) {
            return dataMap.remove(id);
        } else {
            return null;
        }
    }

    protected abstract T doUpdateData(T storedValue, T updateValue);

}
