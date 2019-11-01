package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.RemedyDao;
import ru.tsystems.reha.entity.Remedy;

import java.util.List;

@Service
public class RemedyServiceImpl implements RemedyService {

    private static final Logger LOG = Logger.getLogger(RemedyServiceImpl.class);

    @Autowired
    private RemedyDao remedyDao;

    @Override
    @Transactional
    public List<Remedy> getRemedies() throws ServiceException {
        try {
            return remedyDao.findAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveRemedy(Remedy theRemedy) throws ServiceException {
        try {
            remedyDao.saveOrUpdate(theRemedy);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Remedy getRemedy(int theId) throws ServiceException {
        try {
            return remedyDao.findById(theId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deleteRemedy(int theId) {

    }
}
