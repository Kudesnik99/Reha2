package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
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
import ru.tsystems.reha.jms.JmsProducer;
import ru.tsystems.reha.jms.messages.UpdateEventsListMsg;
import ru.tsystems.reha.listeners.CleanupTransactionListener;
import ru.tsystems.reha.rest.converters.RestEventConverter;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.api.TreatmentService;
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

    private final TreatmentService treatmentService;

    private final JmsProducer jmsProducer;

    private final String UPDATE_MSG = "Update_event";

    private final String DELETE_MSG = "Delete event";

    @Autowired
    public EventServiceImpl(EventDao eventDao, TreatmentDao treatmentDao, TreatmentService treatmentService, JmsProducer jmsProducer) {
        this.eventDao = eventDao;
        this.treatmentDao = treatmentDao;
        this.treatmentService = treatmentService;
        this.jmsProducer = jmsProducer;
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
        boolean needToUpdateTreatment = false;
        UpdateEventsListMsg msg;
        try {
            Event event = eventDao.findById(eventDto.getEventId());
            if (event.getStatus() != eventDto.getStatus()) needToUpdateTreatment = true;
            EventConverter.updateEvent(event, eventDto);
            event.setReason(eventDto.getReason());
            eventDao.saveOrUpdate(event);
            if (needToUpdateTreatment) {
                if (event.getStatus() == EventStatus.CANCELED)
                    msg = new UpdateEventsListMsg("remove");
                else
                    msg = new UpdateEventsListMsg("update");
                msg.addId(event.getEventId());
                registerCleanupTransactionListener(msg);
                updateTreatmentStatus(event.getTreatment());
            }
        } catch (DaoException | ParseException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public List<EventDto> getEventsByTreatmentIdToday(Long treatmentId) throws ServiceException {
        try {
            return eventDao.findByTreatmentIdToday(treatmentId).stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public List<EventDto> getEventsByTreatmentIdThisHour(Long treatmentId) throws ServiceException {
        try {
            return eventDao.findByTreatmentIdThisHour(treatmentId).stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public List<EventDto> getEventsToday() throws ServiceException {
        try {
            return eventDao.findToday().stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public List<EventDto> getEventsThisHour() throws ServiceException {
        try {
            return eventDao.findThisHour().stream().map(EventConverter::toEventDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public ru.tsystems.reha.rest.model.Events getAllEventsForPanel() throws ServiceException {
        List<EventDto> eventsDto = getEventsToday();
        return RestEventConverter.prepareRestEvents(eventsDto);
    }

    @Override
    @Transactional
    public ru.tsystems.reha.rest.model.Events getSomeEventsForPanel(List<Long> ids) throws ServiceException {
        try {
            List<EventDto> eventsDto = eventDao.findByEventIdsToday(ids).stream().map(EventConverter::toEventDto).collect(Collectors.toList());
            return RestEventConverter.prepareRestEvents(eventsDto);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    private void updateTreatmentStatus(Treatment treatment) throws ServiceException {
        try {
            long plannedCount = eventDao.countSomeStatusForTreatment(treatment.getTreatmentId(), EventStatus.PLANNED);
            long executedCount = eventDao.countSomeStatusForTreatment(treatment.getTreatmentId(), EventStatus.EXECUTED);
            long allCount = eventDao.countAllForTreatment(treatment.getTreatmentId());
            long cancelledCount = allCount - (plannedCount + executedCount);

            if (plannedCount < allCount) {
                if (plannedCount == 0) {
                    if (cancelledCount == 0)
                        treatment.setStatus(TreatmentStatus.EXECUTED);
                    else
                        treatment.setStatus(TreatmentStatus.PARTLY_EXECUTED);
                } else treatment.setStatus(TreatmentStatus.PROCESSING);
            }
            treatmentDao.saveTreatment(treatment);
            treatmentService.updatePatientDischargedStatus(treatment.getPatient());
        } catch (
                DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    private void registerCleanupTransactionListener(UpdateEventsListMsg msg) {
        CleanupTransactionListener transactionListener = new CleanupTransactionListener(jmsProducer, msg);
        TransactionSynchronizationManager.registerSynchronization(transactionListener);
    }

}
