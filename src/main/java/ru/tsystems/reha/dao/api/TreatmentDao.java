package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.entity.enums.TreatmentStatus;

import java.util.List;

public interface TreatmentDao extends GenericDao<Treatment, Long> {
    List<Treatment> getTreatments() throws DaoException;

    void saveTreatment(Treatment treatment);

    void deleteTreatment(Long id) throws DaoException;

    List<Treatment> findByPatientId(Long id);

    Long countSomeStatus(TreatmentStatus status) throws DaoException;

    Long countAll() throws DaoException;
}

