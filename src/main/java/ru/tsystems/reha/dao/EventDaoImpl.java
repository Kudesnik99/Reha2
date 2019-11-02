package ru.tsystems.reha.dao;


import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EventDaoImpl extends GenericDaoImpl<Event> implements EventDao {

    @Override
    public List<Event> findByTreatmentId(int treatmentId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            TypedQuery<Event> query = session.createNamedQuery("Event.findByTreatment", Event.class);
            query.setParameter("treatmentId", treatmentId);
            List<Event> result = query.getResultList(); //list();
            if (result.size() < 1) return null;
            else return result; //query.getResultList().get(0);
            //return Optional.ofNullable(query.getResultList().get(0));
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }
}
