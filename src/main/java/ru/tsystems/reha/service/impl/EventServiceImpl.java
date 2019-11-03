package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.converters.EventConverter;
import ru.tsystems.reha.converters.PatientConverter;
import ru.tsystems.reha.dao.api.EventDao;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    private final EventDao eventDao;

    private final TreatmentDao treatmentDao;

    @Autowired
    public EventServiceImpl(EventDao eventDao, TreatmentDao treatmentDao) {
        this.eventDao = eventDao;
        this.treatmentDao = treatmentDao;
    }

    @Override
    @Transactional
    public List<EventDto> getEvents() throws ServiceException {
        try {
            return eventDao.findAll().stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public List<EventDto> getEventsByTreatmentId(Long treatmentId) throws ServiceException {
        try {
            return eventDao.findByTreatmentId(treatmentId).stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public PatientDto getPatientByTreatmentId(Long treatmentId) throws ServiceException {
        return PatientConverter.toPatientDto(treatmentDao.getTreatment(treatmentId).getPatient());
    }

}
