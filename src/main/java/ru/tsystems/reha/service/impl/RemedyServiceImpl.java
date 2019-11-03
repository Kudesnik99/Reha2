package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.converters.RemedyConverter;
import ru.tsystems.reha.dao.api.RemedyDao;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.RemedyDto;
import ru.tsystems.reha.service.api.RemedyService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemedyServiceImpl implements RemedyService {

    private static final Logger LOG = Logger.getLogger(RemedyServiceImpl.class);

    private final RemedyDao remedyDao;

    @Autowired
    public RemedyServiceImpl(RemedyDao remedyDao) {
        this.remedyDao = remedyDao;
    }

    @Override
    @Transactional
    public List<RemedyDto> getRemedies() throws ServiceException {
        try {
            return remedyDao.findAll().stream().map(RemedyConverter::toRemedyDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveRemedy(RemedyDto remedy) throws ServiceException {
        try {
            remedyDao.saveOrUpdate(RemedyConverter.toRemedy(remedy));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public RemedyDto getRemedy(Long id) throws ServiceException {
        try {
            return RemedyConverter.toRemedyDto(remedyDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deleteRemedy(Long id) throws ServiceException {
        try {
            remedyDao.deleteRemedy(id);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }
}
