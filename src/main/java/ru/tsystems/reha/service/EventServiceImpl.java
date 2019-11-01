package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.EventDao;
import ru.tsystems.reha.dao.PatientDao;
import ru.tsystems.reha.dao.UserDao;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public List<Event> getEvents() throws ServiceException {
        try {
            return eventDao.findAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }
}
