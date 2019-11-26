package ru.tsystems.reha.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.tsystems.reha.dao.api.EventDao;
import ru.tsystems.reha.dao.api.PatientDao;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dao.api.UserDao;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.service.api.PatientService;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PatientDao.class, UserDao.class, TreatmentDao.class, EventDao.class})
public class PatientServiceTest {
    private PatientDao mockPatientDao;
    private UserDao mockUserDao;
    private TreatmentDao mockTreatmentDao;
    private EventDao mockEventDao;
    private PatientService patientService;

    @Before
    public void setUp() {
        mockPatientDao = PowerMockito.mock(PatientDao.class);
        mockUserDao = PowerMockito.mock(UserDao.class);
        mockTreatmentDao = PowerMockito.mock(TreatmentDao.class);
        mockEventDao = PowerMockito.mock(EventDao.class);
        patientService = new PatientServiceImpl(mockPatientDao, mockUserDao, mockTreatmentDao, mockEventDao);
    }

    @Test
    public void dischargePatientReady() throws Exception {
        Long id = 1L;
        Date dateFinish = new Date();
        Patient patient = createPatient(id, true, dateFinish, false);
        Patient updatedPatient = createPatient(id, true, new Date(), true);
        when(mockPatientDao.findById(id)).thenReturn(patient);
        when(mockPatientDao.update(patient)).thenReturn(updatedPatient);
        //when(mockPatientDao.update(patient)).then(updatedPatient = createPatient(id, true, new Date(), true));

        assertTrue("Discharged should became true", updatedPatient.getDischarged());
        assertTrue("Finish date should be now!", updatedPatient.getDateFinish().after(patient.getDateFinish()));
    }

    @Test
    public void dischargePatientNotReady() {
        Long id = 1L;
        Date dateFinish = new Date();
        Patient patient = createPatient(id, false, dateFinish, false);

    }

    private Patient createPatient(Long id, Boolean readyToDischarge, Date dateFinish, Boolean discharged) {
        Patient patient = new Patient();
        patient.setPatientId(id);
        patient.setReadyToDischarge(readyToDischarge);
        patient.setDateFinish(dateFinish);
        patient.setDischarged(discharged);
        return patient;
    }

}


