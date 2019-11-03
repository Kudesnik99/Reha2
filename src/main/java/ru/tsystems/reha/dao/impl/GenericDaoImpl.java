package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.reha.dao.api.GenericDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;

import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<T, I extends Serializable> implements GenericDao<T, I> {

    private Class entityClass;

    @Autowired
    public SessionFactory sessionFactory;

    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];
    }

    public T create(T t) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(t);
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    public T update(T t) throws DaoException {
        try {

            Session session = sessionFactory.getCurrentSession();
            session.update(t);
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    public T saveOrUpdate(T t) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(t);
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    public void remove(T t) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(t);
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    public T findById(I id) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return (T) session.get(entityClass, id);
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    public List<T> findAll() throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return  (List<T>) session.createQuery("from " + entityClass.getName(), entityClass).list();
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

}