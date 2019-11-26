package ru.tsystems.reha.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.tsystems.reha.converters.TreatmentConverter;
import ru.tsystems.reha.dao.api.*;
import ru.tsystems.reha.dao.exception.DaoException;
import ru.tsystems.reha.dto.TreatmentDto;
import ru.tsystems.reha.entity.*;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.entity.enums.TreatmentStatus;
import ru.tsystems.reha.jms.JmsProducer;
import ru.tsystems.reha.jms.messages.UpdateEventsListMsg;
import ru.tsystems.reha.listeners.CleanupTransactionListener;
import ru.tsystems.reha.service.api.TreatmentService;
import ru.tsystems.reha.service.exception.ServiceError;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    private final TreatmentDao treatmentDao;

    private final PatientDao patientDao;

    private final PatternDao patternDao;

    private final EventDao eventDao;

    private final RemedyDao remedyDao;

    private final JmsProducer jmsProducer;

    @Autowired
    public TreatmentServiceImpl(TreatmentDao treatmentDao, PatientDao patientDao,
                                PatternDao patternDao, EventDao eventDao, RemedyDao remedyDao, JmsProducer jmsProducer) {
        this.treatmentDao = treatmentDao;
        this.patientDao = patientDao;
        this.patternDao = patternDao;
        this.eventDao = eventDao;
        this.remedyDao = remedyDao;
        this.jmsProducer = jmsProducer;
    }

    @Override
    @Transactional
    public List<TreatmentDto> getTreatments() throws ServiceException {
        try {
            return treatmentDao.findAll().stream().map(TreatmentConverter::toTreatmentDto).collect(Collectors.toList());
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveTreatment(TreatmentDto treatmentDto) throws ServiceException {
        try {
            Treatment treatment;
            if (treatmentDto.getTreatmentId() == null) treatment = TreatmentConverter.toTreatment(treatmentDto);
            else {
                treatment = treatmentDao.findById(treatmentDto.getTreatmentId());
                TreatmentConverter.updateTreatment(treatment, treatmentDto);
            }
            Patient patient = patientDao.findById(treatmentDto.getPatientDto().getPatientId());
            Pattern pattern = patternDao.findById(treatmentDto.getPatternDto().getPatternId());
            Remedy remedy = remedyDao.findById(treatmentDto.getRemedyDto().getRemedyId());
            treatment.setPatient(patient);
            treatment.setTimePattern(pattern);
            treatment.setRemedy(remedy);
            treatmentDao.saveOrUpdate(treatment);
            updatePatientDischargedStatus(treatment.getPatient());

        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }


    @Override
    @Transactional
    public TreatmentDto getTreatment(Long id) throws ServiceException {
        try {
            return TreatmentConverter.toTreatmentDto(treatmentDao.findById(id));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deleteTreatment(Long treatmentId) throws ServiceException {
        try {
            Patient patient = treatmentDao.findById(treatmentId).getPatient();
            treatmentDao.deleteTreatment(treatmentId);
            updatePatientDischargedStatus(patient);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Patient getPatientByPatientId(Long id) throws ServiceException {
        try {
            return patientDao.findById(id);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Transactional
    @Override
    public List<TreatmentDto> getTreatmentsByPatientId(Long patientId) throws ServiceException {
        try {
            return treatmentDao.findByPatientId(patientId).stream().map(TreatmentConverter::toTreatmentDto).collect(Collectors.toList());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    @Transactional
    @Override
    public void generateEvents(Long treatmentId) throws ServiceException {
        UpdateEventsListMsg msg = new UpdateEventsListMsg("add");
        try {
            Treatment treatment = treatmentDao.findById(treatmentId);
            Long patternId = treatment.getTimePattern().getPatternId();
            Pattern pattern = patternDao.findById(patternId);
            CronSequenceGenerator generator = new CronSequenceGenerator(pattern.getPatternTemplate());
            Date eventDate = treatment.getPeriodStart();
            Event event;
            do {
                eventDate = generator.next(eventDate);
                event = new Event();
                event.setDateTime(eventDate);
                event.setTreatment(treatment);
                event.setStatus(EventStatus.PLANNED);
                eventDao.saveOrUpdate(event);
                msg.addId(event.getEventId());
            } while (eventDate.before(treatment.getPeriodEnd()));
            registerCleanupTransactionListener(msg);
            treatment.setStatus(TreatmentStatus.ASSIGNED);
            treatmentDao.saveTreatment(treatment);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    public void updatePatientDischargedStatus(Patient patient) throws ServiceException {
        long executedCount;
        long partlyExecutedCount;
        long allCount;

        try {
            partlyExecutedCount = treatmentDao.countSomeStatusForPatient(patient.getPatientId(), TreatmentStatus.PARTLY_EXECUTED);
            executedCount = treatmentDao.countSomeStatusForPatient(patient.getPatientId(), TreatmentStatus.EXECUTED);
            allCount = treatmentDao.countAllForPatient(patient.getPatientId());

            if (allCount == (partlyExecutedCount + executedCount)) patient.setReadyToDischarge(true);
            else patient.setReadyToDischarge(false);
            patientDao.update(patient);
        } catch(
                DaoException e)

        {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ServiceError.PERSIST_EXCEPTION, e);
        }
    }

    public List<TreatmentStatus> getTreatmentStatusList(TreatmentStatus currentStatus) {
        if (currentStatus == TreatmentStatus.ASSIGNED) return Arrays.asList(currentStatus, TreatmentStatus.CANCELED);
        else if (currentStatus == TreatmentStatus.PROCESSING) return Arrays.asList(currentStatus, TreatmentStatus.PARTLY_EXECUTED);
        else return Arrays.asList(TreatmentStatus.values());
    }

    private void registerCleanupTransactionListener(UpdateEventsListMsg msg) {
        CleanupTransactionListener transactionListener = new CleanupTransactionListener(jmsProducer, msg);
        TransactionSynchronizationManager.registerSynchronization(transactionListener);
    }
}
