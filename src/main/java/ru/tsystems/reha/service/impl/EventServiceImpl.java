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
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.entity.enums.TreatmentStatus;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final Logger LOG = Logger.getLogger(EventServiceImpl.class);

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
        try {
            return PatientConverter.toPatientDto(treatmentDao.findById(treatmentId).getPatient());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public EventDto getEvent(Long id) throws ServiceException {
        try {
            return EventConverter.toEventDto(eventDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveEvent(EventDto eventDto) throws ServiceException {
        try {
            boolean needToUpdateTreatment = false;

            Event event = eventDao.findById(eventDto.getEventId());
            if (event.getStatus() != eventDto.getStatus()) needToUpdateTreatment = true;
            EventConverter.updateEvent(event, eventDto);
            eventDao.saveOrUpdate(event);
            if (needToUpdateTreatment) updateTreatmentStatus(event.getTreatment());
        } catch (DaoException | ParseException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    private void updateTreatmentStatus(Treatment treatment) throws ServiceException {
        long executedCount;
        long plannedCount;
        long allCount;
        long cancelledCount;

        try {
        plannedCount = eventDao.countSomeStatus(EventStatus.PLANNED);
        executedCount = eventDao.countSomeStatus(EventStatus.EXECUTED);
        allCount = eventDao.countAll();
        cancelledCount = allCount - (plannedCount + executedCount);

        if (plannedCount < allCount) {
            if (plannedCount == 0) {
                if (cancelledCount == 0)
                    treatment.setStatus(TreatmentStatus.EXECUTED);
                else
                    treatment.setStatus(TreatmentStatus.PARTLY_EXECUTED);
            } else treatment.setStatus(TreatmentStatus.PROCESSING);
        }
                treatmentDao.saveTreatment(treatment);
        } catch(
                DaoException e)

        {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

}
