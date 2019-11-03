package ru.tsystems.reha.dao.api;

import ru.tsystems.reha.entity.User;

public interface UserDao extends GenericDao<User, Long> {
    User findByEmail(String email);
}
