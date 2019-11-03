package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Remedy;

public interface RemedyDao extends GenericDao<Remedy, Long> {
    void deleteRemedy(Long remedyId) throws DaoException;
}
