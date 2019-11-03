package ru.tsystems.reha.dao;

import ru.tsystems.reha.entity.Remedy;

public interface RemedyDao extends GenericDao<Remedy> {
    void deleteRemedy(int remedyId) throws DaoException;
}
