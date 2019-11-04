package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Treatment;

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
}
