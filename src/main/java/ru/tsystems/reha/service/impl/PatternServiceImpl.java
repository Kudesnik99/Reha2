package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.converters.PatternConverter;
import ru.tsystems.reha.dao.api.PatternDao;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.PatternDto;
import ru.tsystems.reha.service.api.PatternService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatternServiceImpl implements PatternService {

    private static final Logger LOG = Logger.getLogger(PatternServiceImpl.class);

    private final PatternDao patternDao;

    @Autowired
    public PatternServiceImpl(PatternDao patternDao) {
        this.patternDao = patternDao;
    }

    @Override
    @Transactional
    public List<PatternDto> getPatterns() throws ServiceException {
        try {
            return patternDao.findAll().stream().map(PatternConverter::toPatternDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public PatternDto getPattern(Long id) throws ServiceException {
        try {
            return PatternConverter.toPatternDto(patternDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void savePattern(PatternDto pattern) throws ServiceException {
        try {
           patternDao.saveOrUpdate(PatternConverter.toPattern(pattern));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deletePattern(Long id) throws ServiceException {
        try {
            patternDao.remove(patternDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }

    }
}
