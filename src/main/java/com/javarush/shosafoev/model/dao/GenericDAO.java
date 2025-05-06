package com.javarush.shosafoev.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@SuppressWarnings({"unchecked", "RedundantCast", "unused"})
public class GenericDAO<T> {
    private final Class<T> aClass;
    private final SessionFactory sessionFactory;

    public GenericDAO(final Class<T> aClass, SessionFactory sessionFactory) {
        this.aClass = aClass;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return (T) getCurrentSession().get(aClass, id);
    }

    public List<T> getItems(int offset, int limit) {
        return getCurrentSession()
                .createQuery(
                        "FROM " + aClass.getName()
                        , aClass
                )
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession()
                .createQuery(
                        "FROM " + aClass.getName(), aClass
                ).list();
    }

    @SuppressWarnings("UnusedReturnValue")
    public T save(final T entity) {
        getCurrentSession().save(entity);
        return entity;
    }

    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}