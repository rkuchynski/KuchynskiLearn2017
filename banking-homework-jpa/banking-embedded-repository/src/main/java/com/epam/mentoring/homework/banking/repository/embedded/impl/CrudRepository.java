package com.epam.mentoring.homework.banking.repository.embedded.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * Generic CRUD repository.
 * <p/>
 * Date: 06/12/2017
 *
 * @author Raman Kuchynski
 */
@Repository
@Transactional
public class CrudRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * Read data by id.
     * @param type entity type.
     * @param id id.
     * @param <T> entity type param.
     * @return entity value.
     */
    public <T extends Serializable> T read(final Class<T> type, long id) {
        return getSession().get(type, id);
    }

    /**
     * Store data.
     * @param type entity type.
     * @param data entity object.
     * @param <T> entity type param.
     * @return entity value.
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> T store(final Class<T> type, T data) {
        Long generatedId = (Long) getSession().save(data);
        return read(type, generatedId);
    }

    /**
     * Update data.
     * @param data entity object.
     * @param <T> entity type param.
     * @return entity value.
     */
    public <T extends Serializable> T update(T data) {
        getSession().update(data);
        return data;
    }

    /**
     * Delete data by id.
     * @param type entity type.
     * @param id id.
     * @param <T> entity type param.
     * @return entity value.
     */
    public <T extends Serializable> T delete(final Class<T> type, Long id) {
        T data = getSession().get(type, id);
        getSession().delete(data);
        return data;
    }

    /**
     * Get all values from DB.
     * @param type entity type.
     * @param <T> entity type param.
     * @return list of entity values.
     */
    @SuppressWarnings("unchecked")
    public <T extends Serializable> List<T> getAll(final Class<T> type) {
        return getSession().createCriteria(type).list();
    }

    /**
     * Delete all entities from DB.
     * @param type entity type.
     * @param <T> entity type param.
     */
    public <T extends Serializable> void deleteAll(final Class<T> type) {
        String entityName = getSession().getEntityName(type);
        getSession().createQuery("DELETE * FROM " + entityName + ";").executeUpdate();
    }

}
