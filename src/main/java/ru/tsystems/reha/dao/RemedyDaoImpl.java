package ru.tsystems.reha.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.entity.Remedy;
import ru.tsystems.reha.entity.Treatment;

import javax.persistence.PersistenceException;

@Repository
public class RemedyDaoImpl extends GenericDaoImpl<Remedy> implements RemedyDao {
    @Override
    public void deleteRemedy(int remedyId) throws DaoException {
        try {
            Session session = sessionFactory.getCurrentSession();
            Remedy remedy = session.byId(Remedy.class).load(remedyId);
            remove(remedy);
        } catch (PersistenceException e) {
            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
        }
    }
}
