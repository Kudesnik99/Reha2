package ru.tsystems.reha.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private static Logger logger = Logger.getLogger(GenericDaoImpl.class);

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
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    public T update(T t) throws DaoException {
        try {

            Session session = sessionFactory.getCurrentSession();
            session.update(t);
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    public T saveOrUpdate(T t) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(t);
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    public void remove(T t) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(t);
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    public T findById(Integer id) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            return (T) session.get(entityClass, id);
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    public List<T> findAll() throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            List<T> t = (List<T>) session.createQuery("from " + entityClass.getName(), entityClass).list();
            return t;
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

}