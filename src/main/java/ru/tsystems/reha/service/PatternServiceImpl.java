package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.PatternDao;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Pattern;
import ru.tsystems.reha.model.PatientForm;
import ru.tsystems.reha.model.PatientFormConverter;

import java.util.List;

@Service
public class PatternServiceImpl implements PatternService {

    private static final Logger LOG = Logger.getLogger(PatternServiceImpl.class);

    @Autowired
    private PatternDao patternDao;

    @Override
    @Transactional
    public List<Pattern> getPatterns() throws ServiceException {
        try {
            return patternDao.findAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Pattern getPattern(int theId) throws ServiceException {
        try {
            return patternDao.findById(theId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void savePattern(Pattern pattern) throws ServiceException {
        try {
           patternDao.saveOrUpdate(pattern);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deletePattern(int theId) throws ServiceException {
        try {
            patternDao.remove(patternDao.findById(theId));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }

    }
}
