package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.dao.api.RemedyDao;
import ru.tsystems.reha.dao.exception.DaoError;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.entity.Remedy;

import javax.persistence.PersistenceException;

@Repository
public class RemedyDaoImpl extends GenericDaoImpl<Remedy, Long> implements RemedyDao {
    @Override
    public void deleteRemedy(Long remedyId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Remedy remedy = session.byId(Remedy.class).load(remedyId);
            remove(remedy);
        } catch (PersistenceException e) {
            throw new DaoException(DaoError.PERSIST_EXCEPTION, e);
        }
    }
}
