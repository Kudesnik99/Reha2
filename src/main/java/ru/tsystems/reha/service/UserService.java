package ru.tsystems.reha.service;

import ru.tsystems.reha.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getUsers() throws ServiceException;

    public void saveUser(User theUser) throws ServiceException;

    public User getUser(int theId) throws ServiceException;

    public void deleteUser(int theId);

}
