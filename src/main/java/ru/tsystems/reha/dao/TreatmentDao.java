package ru.tsystems.reha.dao;

import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.User;

import java.util.List;

public interface TreatmentDao extends GenericDao<Treatment>{
    public List<Treatment> getTreatments() throws DaoException;

    public void saveTreatment(Treatment theTreatment);

    public Treatment getTreatment(int theId);

    public void deleteTreatment(int theId);

    public List<Treatment> findByPatientId(int theId);
}

