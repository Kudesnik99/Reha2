package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Treatment;
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
    public List<Treatment> findByPatientId(Long patientId) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findByPatient", Treatment.class);
        query.setParameter("patientId", patientId);
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

    @Override
    public List<Treatment> findByPatientAndStatus(Long patientId, TreatmentStatus status) {
        Session session = sessionFactory.getCurrentSession();

        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findByPatientAndStatus", Treatment.class);
        query.setParameter("patientId", patientId);
        query.setParameter("status", status);
        return query.getResultList();
    }

    @Override
    public List<Treatment> findByPatientAndThreeStatuses(Long patientId, TreatmentStatus status1,
                                                    TreatmentStatus status2, TreatmentStatus status3) {
        Session session = sessionFactory.getCurrentSession();

        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findByPatientAndThreeStatuses", Treatment.class);
        query.setParameter("patientId", patientId);
        query.setParameter("status1", status1);
        query.setParameter("status2", status2);
        query.setParameter("status3", status3);
        return query.getResultList();
    }

    @Override
    public Long countSomeStatusForPatient(Long patientId, TreatmentStatus status) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Treatment.countSomeStatusForPatient", Long.class);
        query.setParameter("patientId", patientId);
        query.setParameter("status", status);
        return query.uniqueResult();
    }

    @Override
    public Long countAllForPatient(Long patientId) throws DaoException {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createNamedQuery("Treatment.countAllForPatient", Long.class);
        query.setParameter("patientId", patientId);
        return query.uniqueResult();
    }

    @Override
    public List<Treatment> findAll() throws DaoException {
        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("select tr from Treatment tr order by tr.patient.patientId", Treatment.class).list();
        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findAllSortedByPatient", Treatment.class);
        return query.getResultList();
    }
}
