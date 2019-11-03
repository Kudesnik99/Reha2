package ru.tsystems.reha.service.api;

import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<UserDto> getUsers() throws ServiceException;

    void saveUser(UserDto user) throws ServiceException;

    UserDto getUser(Long id) throws ServiceException;

    void deleteUser(Long id);
}
