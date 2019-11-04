package ru.tsystems.reha.dao.impl;


import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.EventDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.enums.EventStatus;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event, Long> implements EventDao {

    @Override
    public List<Event> findByTreatmentId(Long treatmentId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            TypedQuery<Event> query = session.createNamedQuery("Event.findByTreatment", Event.class);
            query.setParameter("treatmentId", treatmentId);
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    public Long countSomeStatus(EventStatus status) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Event.countSomeStatus", Long.class);
        query.setParameter("status", status);
        return query.uniqueResult();
    }

    @Override
    public Long countAll() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Event.countAll", Long.class);
        return query.uniqueResult();

    }
}
