package ru.tsystems.reha.service;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.reha.dao.DaoException;
import ru.tsystems.reha.dao.PatientDao;
import ru.tsystems.reha.dao.PatternDao;
import ru.tsystems.reha.dao.TreatmentDao;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Pattern;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.PatientFormConverter;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;

import java.lang.reflect.InvocationTargetException;
import java.security.Timestamp;
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
    public void deleteTreatment(int theId) {
        treatmentDao.deleteTreatment(theId);
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
    public List<Treatment> getTreatmentsByPatientId(int theId) throws ServiceException {
        try {
            //return patientDao.findById(theId);
            return treatmentDao.findByPatientId(theId);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
    }

    @Transactional
    @Override
    public List<Event> generateEvents(int id) throws ServiceException {
        try {
            Treatment treatment = treatmentDao.findById(id);
            int patternId = treatment.getTimePattern().getPatternId();
            Pattern pattern = patternDao.findById(patternId);
            CronSequenceGenerator generator = new CronSequenceGenerator(pattern.getPatternTemplate());
            Date eventDate = treatment.getPeriod_start();
            Event event;
            do {
                eventDate = generator.next(eventDate);
                //ToDo: create Event


            } while (eventDate != null && (eventDate.before(treatment.getPeriod_end())));
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(ErrorService.PERSIST_EXCEPTION, e);
        }
        return null;
    }
}
