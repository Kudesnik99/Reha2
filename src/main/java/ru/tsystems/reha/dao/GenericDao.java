package ru.tsystems.reha.dao;

import java.util.List;

public interface GenericDao<T> {

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
    T findById(Integer id) throws DaoException;

    /*
     * Method reads all specified entities from database
     */
    List<T> findAll() throws DaoException;
}