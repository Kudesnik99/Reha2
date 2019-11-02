package ru.tsystems.reha.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.User;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TreatmentDaoImpl extends GenericDaoImpl<Treatment> implements TreatmentDao {

    @Override
    public List<Treatment> getTreatments() throws DaoException {
        try {
            return findAll();
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    public void deleteTreatment(int id) {
        Session session = sessionFactory.getCurrentSession();
        Treatment book = session.byId(Treatment.class).load(id);
        session.delete(book);
    }

    @Override
    public void saveTreatment(Treatment theTreatment) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theTreatment);
    }

    @Override
    public Treatment getTreatment(int theId) {
        return new Treatment();
    }

    @Override
    public List<Treatment> findByPatientId(int patientId) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Treatment> query = session.createNamedQuery("Treatment.findByPatient", Treatment.class);
        query.setParameter("patientId", patientId);
        List<Treatment> result = query.getResultList(); //list();
        if (result.size() < 1) return null;
        else return result; //query.getResultList().get(0);
        //return Optional.ofNullable(query.getResultList().get(0));
    }
}
