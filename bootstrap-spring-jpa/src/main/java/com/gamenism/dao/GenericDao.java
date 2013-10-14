package com.gamenism.dao;

import java.util.Map;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 1:02 AM
 */
public interface GenericDao<T> {
    /**
     * Method that returns the number of entries from a table that meet some
     * criteria (where clause params)
     *
     * @param params sql parameters
     * @return the number of records meeting the criteria
     */
    long countAll(Map params);

    T create(T t);

    void delete(Object id);

    T find(Object id);

    T update(T t);

    void clear();
}
