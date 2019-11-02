package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.*;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Pattern;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TreatmentDao treatmentDao;

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

    @Override
    @Transactional
    public List<Event> getEventsByTreatmentId(int treatmentId) throws ServiceException {
        try {
            return eventDao.findByTreatmentId(treatmentId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Patient getPatientByTreatmentId(int treatmentId) throws ServiceException {
        return treatmentDao.getTreatment(treatmentId).getPatient();
    }

    @Override
    @Transactional
    public void saveEvents(Event event) throws ServiceException {
        try {
            eventDao.saveOrUpdate(event);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }
}
