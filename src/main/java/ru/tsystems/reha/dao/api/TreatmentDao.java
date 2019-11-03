package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Treatment;

import java.util.List;

public interface TreatmentDao extends GenericDao<Treatment, Long> {
    List<Treatment> getTreatments() throws DaoException;

    void saveTreatment(Treatment treatment);

    Treatment getTreatment(Long id);

    void deleteTreatment(Long id) throws DaoException;

    List<Treatment> findByPatientId(Long id);
}
