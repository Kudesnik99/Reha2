package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.enums.EventStatus;

import java.util.List;

public interface EventDao extends GenericDao<Event, Long> {
    List<Event> findByTreatmentId(Long treatmentId) throws DaoException;

    Long countSomeStatus(EventStatus status) throws DaoException;

    Long countSomeStatusForTreatment(Long treatmentId, EventStatus status) throws DaoException;

    Long countAll() throws DaoException;

    List<Event> findByTreatmentAndStatus(Long treatmentId, EventStatus status) throws DaoException;

    Long countAllForTreatment(Long treatmentId) throws DaoException;

    List<Event> findByTreatmentIdToday(Long id) throws DaoException;

    List<Event> findByTreatmentIdThisHour(Long id) throws DaoException;

    List<Event> findToday() throws DaoException;

    List<Event> findThisHour() throws DaoException;

    List<Event> findByEventIdsToday(List<Long> ids) throws DaoException;;
}
