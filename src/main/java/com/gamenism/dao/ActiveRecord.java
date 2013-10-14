package com.gamenism.dao;

import com.gamenism.model.BaseEntity;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 12:11 AM
 */
@Configurable
@NamedQueries({
        @NamedQuery(name="findUserByEmail", query="select u from User u where u.email = ?")
})
public class ActiveRecord<T> extends BaseEntity implements Serializable{
    private Class<T> persistentClass;

    @PersistenceContext
    transient EntityManager entityManager;

    public ActiveRecord() {
    }

    public ActiveRecord(Class c) {
        this();
        this.persistentClass = c;
    }

    public static final EntityManager entityManager() {
        EntityManager em = new ActiveRecord().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    @Transactional
    public void persist(BaseEntity entity) {
        entityManager.persist(entity);
    }

    /**
     * Deletes from database
     */
    @Transactional
    public <T> void remove() {
        if (entityManager == null) entityManager = entityManager();
        if (entityManager.contains(this)) {
            entityManager.remove(this);
        } else {
            T attached = (T) find(this.getId());
            entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (entityManager == null) entityManager = entityManager();
        entityManager.flush();
    }

    /**
     *  Clears the persistence context
     */
    @Transactional
    public void clear() {
        entityManager.clear();
    }

    @Transactional
    public void merge() {
        setModifyDate(new Date());
        entityManager.merge(this);
        entityManager.flush();
    }

    public long count() {
        return entityManager().createQuery("SELECT COUNT(o) FROM " + this.getClass().getName() + " o", Long.class).getSingleResult();
    }

    public <A> Long count(Class<A> cls) {
        return entityManager().createQuery("SELECT COUNT(o) FROM " + cls.getName() + " o", Long.class).getSingleResult();
    }

//    public T find(Long id) {
//        return entityManager().find(persistentClass, id);
//    }

    public T find(Long id) {
        return entityManager.find(persistentClass, id);
    }

//    public List<T> findAll() {
//        return entityManager().createQuery("SELECT o FROM " + persistentClass.getName() + " o", persistentClass).getResultList();
//    }

    public <A> List<A> findAll(Class<A> cls) {
        return entityManager().createQuery("SELECT o FROM " + cls.getName() + " o", cls).getResultList();
    }

//    public List<T> findEntries(int firstResult, int maxResults) {
//        return entityManager().createQuery("SELECT o FROM " + persistentClass.getName() + " o", persistentClass)
//                .setFirstResult(firstResult)
//                .setMaxResults(maxResults)
//                .getResultList();
//    }

    public <A> List<A> findEntries(Class<A> cls, int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM " + cls.getName() + " o", cls)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }

    public T getSingleResultFromNamedQuery(String namedQuery, String... params) {
        TypedQuery<T> query = entityManager.createNamedQuery(namedQuery, persistentClass);
        int paramIndex = 1;
        for (String param : params) {
            query.setParameter(paramIndex++, param);
        }

        try {
            return query.getSingleResult();
        } catch(RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause instanceof NoResultException) {
                return null;
            }

            throw e;
        }
    }

}
