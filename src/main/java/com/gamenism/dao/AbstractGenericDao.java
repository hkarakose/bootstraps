package com.gamenism.dao;

import com.gamenism.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * User: halil
 * Date: 9/28/13
 * Time: 1:02 AM
 */
public class AbstractGenericDao<T> implements GenericDao<T> {
    @PersistenceContext
    protected EntityManager em;

    private Class<T> type;

    public AbstractGenericDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Transactional
    @Override
    public long countAll(final Map params) {
        return em.createQuery("SELECT COUNT(o) FROM " + type.getName() + " o", Long.class).getSingleResult();
    }

    @Transactional
    @Override
    public T create(final T t) {
        em.persist(t);
        return t;
    }

    @Transactional
    @Override
    public void delete(final Object id) {
        this.em.remove(this.em.getReference(type, id));
    }

    @Transactional
    @Override
    public T find(final Object id) {
        return (T) this.em.find(type, id);
    }

    @Transactional
    @Override
    public T update(final T t) {
        return this.em.merge(t);
    }

    @Transactional
    @Override
    public void clear() {
        em.clear();
    }
}
