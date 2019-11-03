package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, I extends Serializable> {

    /*
     * Method writes an entity to the database
     */
    T create(T t) throws DaoException;

    /*
     * Method writes the changes of the entity
     * to the database
     */
    T update(T t) throws DaoException;

    /*
     * Method writes the changes of the entity
     * to the database
     */
    T saveOrUpdate(T t) throws DaoException;

    /*
     * Method removes an entity from the database
     */
    void remove(T t) throws DaoException;

    /*
     * Method reads an entity from the database
     * by entity Id
     */
    T findById(I id) throws DaoException;

    /*
     * Method reads all specified entities from database
     */
    List<T> findAll() throws DaoException;
}