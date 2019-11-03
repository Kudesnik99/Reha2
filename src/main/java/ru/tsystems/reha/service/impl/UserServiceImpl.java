package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.api.UserDao;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.User;
import ru.tsystems.reha.service.api.UserService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<UserDto> getUsers() throws ServiceException {
        try {
            return userDao.findAll().stream().map(UserDto::new).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveUser(UserDto userDto) throws ServiceException {
        try {
            User user = userDao.findByEmail(userDto.getEmail());
            updateUser(user, userDto);
            userDao.saveOrUpdate(user);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    private void updateUser(User user, UserDto userDto) {
        // todo: fill user
    }

    @Override
    @Transactional
    public UserDto getUser(Long id) throws ServiceException {
        try {
            return new UserDto(userDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        //TODO: implement me
    }
}
