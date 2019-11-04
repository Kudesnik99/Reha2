package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.entity.enums.TreatmentStatus;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TreatmentDaoImpl extends GenericDaoImpl<Treatment, Long> implements TreatmentDao {

    @Override
    public List<Treatment> getTreatments() throws DaoException {
        try {
            return findAll();
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    public void deleteTreatment(Long id) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Treatment treatment = session.byId(Treatment.class).load(id);
            remove(treatment);
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    public void saveTreatment(Treatment treatment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(treatment);
    }

    @Override
    public List<Treatment> findByPatientId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findByPatient", Treatment.class);
        query.setParameter("patientId", id);
        return query.getResultList();
    }

    @Override
    public Long countSomeStatus(TreatmentStatus status) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Treatment.countSomeStatus", Long.class);
        query.setParameter("status", status);
        return query.uniqueResult();
    }

    @Override
    public Long countAll() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Treatment.countAll", Long.class);
        return query.uniqueResult();

    }
}
