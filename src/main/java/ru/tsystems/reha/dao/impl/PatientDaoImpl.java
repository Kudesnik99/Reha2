package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.tsystems.reha.dao.api.PatientDao;
import ru.tsystems.reha.entity.Patient;

import java.util.List;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient, Long> implements PatientDao {

    public Patient findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("Patient.findByEmail");
        query.setParameter("email", email);
        List result = query.list();
        if (CollectionUtils.isEmpty(result)) return null;
        else return (Patient) query.getResultList().get(0);
    }
}
