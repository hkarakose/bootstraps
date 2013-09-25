package org.bootstrap.model;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 12:11 AM
 */
@Configurable
public class ActiveRecord extends BaseEntity implements Serializable{
    @PersistenceContext
    transient EntityManager entityManager;

    public ActiveRecord() {
//        Type parametrizedType = getClass().getGenericSuperclass();
//        //required because of initializations such as "new ActiveRecord()"
//        if (parametrizedType instanceof ParameterizedType) {
//            this.persistentClass = (Class<T>) ((ParameterizedType) parametrizedType).getActualTypeArguments()[0];
//        }
    }

    public static final EntityManager entityManager() {
        EntityManager em = new ActiveRecord().entityManager;
        if (em == null)
            throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    @Transactional
    public void persist() {
        entityManager.persist(this);
    }

    /**
     * Deletes from database
     */
    @Transactional
    public <A> void remove(Class<A> cls) {
        if (entityManager == null) entityManager = entityManager();
        if (entityManager.contains(this)) {
            entityManager.remove(this);
        } else {
            A attached = find(cls, this.getId());
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

    public <A> A find(Class<A> cls, Long id) {
        if (entityManager == null) entityManager = entityManager();
        return entityManager.find(cls, id);
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

}
