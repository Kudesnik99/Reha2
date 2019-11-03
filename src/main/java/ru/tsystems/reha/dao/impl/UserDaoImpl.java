package ru.tsystems.reha.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.tsystems.reha.dao.api.UserDao;
import ru.tsystems.reha.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("User.findByEmail");
        query.setParameter("email", email);
        List result = query.list();
        if (CollectionUtils.isEmpty(result)) return null;
        else return (User) query.getResultList().get(0);
    }

}
