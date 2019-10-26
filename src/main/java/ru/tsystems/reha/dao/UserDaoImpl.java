package ru.tsystems.reha.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.tsystems.reha.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        List result = query.list();
        if (result.size() < 1) return null;
        else return (User) query.getResultList().get(0);
        //return Optional.ofNullable(query.getResultList().get(0));
    }

//    @Override
//    public List<User> getUsers() throws DaoException {
//        try {
//            return findAll();
//        } catch (PersistenceException e) {
//            throw new DaoException(ErrorDao.PERSIST_EXCEPTION, e);
//        }
//    }
//
//    @Override
//    public void saveUser(User theUser) {
//
//    }
//
//    @Override
//    public User getUser(int theId) {
//        return null;
//    }
//
//    @Override
//    public void deleteUser(int theId) {
//
//    }
}
