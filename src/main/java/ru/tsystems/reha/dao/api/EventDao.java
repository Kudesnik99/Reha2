package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Event;

import java.util.List;

public interface EventDao extends GenericDao<Event, Long> {
    List<Event> findByTreatmentId(Long treatmentId) throws DaoException;
}