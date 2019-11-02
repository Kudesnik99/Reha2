package ru.tsystems.reha.dao;

import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;

import java.util.List;

public interface EventDao extends GenericDao<Event> {
    List<Event> findByTreatmentId(int treatmentId) throws DaoException;

    //Patient getPatientByTreatmentId(int treatmentId) throws DaoException;
}
