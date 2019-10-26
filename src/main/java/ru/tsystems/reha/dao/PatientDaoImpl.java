package ru.tsystems.reha.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.entity.Patient;

import java.util.List;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient> implements PatientDao {

        public Patient findByEmail(String email) {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.getNamedQuery("Patient.findByEmail");
            query.setParameter("email", email);
            List result = query.list();
            if (result.size() < 1) return null;
            else return (Patient) query.getResultList().get(0);
            //return Optional.ofNullable(query.getResultList().get(0));
        }
}
