package ru.tsystems.reha.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.*;
import ru.tsystems.reha.entity.*;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    private static final Logger LOG = Logger.getLogger(TreatmentServiceImpl.class);

    @Autowired
    private TreatmentDao treatmentDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private PatternDao patternDao;

    @Autowired
    private EventDao eventDao;

    @Override
    @Transactional
    public List<Treatment> getTreatments() throws ServiceException {
        try {
            return treatmentDao.findAll(); //getTreatments();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void saveTreatment(TreatmentForm treatmentForm) throws ServiceException {
        try {

            Patient patient = patientDao.findById(treatmentForm.getPatientId());
//            patient.setDoctor(userDao.findByEmail(doctorEmail));
//            patientDao.saveOrUpdate(patient);

            treatmentForm.setPatient(patient);
            Treatment treatment = TreatmentFormConverter.toTreatment(treatmentForm);
            //treatment.setPatient(patientDao.findById());
            treatmentDao.saveOrUpdate(treatment);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }


    @Override
    @Transactional
    public Treatment getTreatment(int id) throws ServiceException {
        try {
            return treatmentDao.findById(id);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public void deleteTreatment(int treatmentId) throws ServiceException {
        try {
            treatmentDao.deleteTreatment(treatmentId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Override
    @Transactional
    public Patient getPatientByPatientId(int theId) throws ServiceException {
        try {
            return patientDao.findById(theId);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Transactional
    @Override
    public List<Treatment> getTreatmentsByPatientId(int patientId) throws ServiceException {
        try {
            //return patientDao.findById(theId);
            return treatmentDao.findByPatientId(patientId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Transactional
    @Override
    public List<Event> generateEvents(int treatmentId) throws ServiceException {
        try {

            List<Event> result = new ArrayList<>();
            Treatment treatment = treatmentDao.findById(treatmentId);
            int patternId = treatment.getTimePattern().getPatternId();
            Pattern pattern = patternDao.findById(patternId);
            CronSequenceGenerator generator = new CronSequenceGenerator(pattern.getPatternTemplate());
            Date eventDate = treatment.getPeriod_start();
            Event event;
            do {
                eventDate = generator.next(eventDate);
                event = new Event();
                event.setDateTime(eventDate);
                event.setTreatment(treatment);
                event.setStatus(EventStatus.PLANNED);
                eventDao.saveOrUpdate(event);
                result.add(event);
            } while (eventDate != null && (eventDate.before(treatment.getPeriod_end())));
            return result;
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }
}
