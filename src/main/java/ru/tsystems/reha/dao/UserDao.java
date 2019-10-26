package ru.tsystems.reha.dao;

import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);

//    public List<User> getUsers() throws DaoException;
//
//    public void saveUser(User theUser);
//
//    public User getUser(int theId);
//
//    public void deleteUser(int theId);
}
