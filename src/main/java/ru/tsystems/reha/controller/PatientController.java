package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.model.PatientForm;
import ru.tsystems.reha.service.PatientService;
import ru.tsystems.reha.service.ServiceException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Process requests related with patients.
 *  * @author Laristov Alexey
 */
@Controller
@RequestMapping("/patient")
public class PatientController {
    private static final Logger LOG = Logger.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String listPatients(Model model, Authentication authentication) {
        try {
            List<Patient> thePatients = patientService.getPatients();
            model.addAttribute("patients", thePatients);
            //UserDto userDto = (UserDto) authentication.getPrincipal();
            model.addAttribute("userDto", authentication.getPrincipal()); //userDto);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        }
        return "list-patients";
    }

    /**
     * Show form for add patient.
     * @param model          - data model
     * @param authentication - current user
     * @return               - name of form page
     */
    @GetMapping("/addPatient")
    public String showFormForAdd(Model model, Authentication authentication) {
        Patient patient = new Patient();
        model.addAttribute("patient", patient);

        UserDto userDto = (UserDto) authentication.getPrincipal();
        model.addAttribute("userDto", userDto);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        theModel.addAttribute("userDetails", userDetails);
        return "patient-form";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") PatientForm thePatient, Authentication authentication) {
        try {
            patientService.savePatient(thePatient, ((UserDto) authentication.getPrincipal()).getEmail());
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/patient/list";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("patientId") int theId,
                                    Model theModel, Authentication authentication) {
        try {
            Patient thePatient = patientService.getPatient(theId);
            theModel.addAttribute("patient", thePatient);
            UserDto userDto = (UserDto) authentication.getPrincipal();
            theModel.addAttribute("userDto", userDto);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "patient-form";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("patientId") int theId) {
        try {
            patientService.deletePatient(theId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/patient/list";
    }

    @GetMapping("/discharge")
    public String dischargePatient(@RequestParam("patientId") Long id) {
        try {
            patientService.dischargePatient(id);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/patient/list?patientId=0?showConfirm";
    }

    @GetMapping("/dischargeForced")
    public String dischargePatientForced(@RequestParam("patientId") Long id) {
        try {
            patientService.dischargePatientForced(id);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/patient/list?patientId=0?showConfirm";
    }

    @GetMapping("/treatments")
    public String showUserTreatments(@RequestParam("patientId") int theId,
                                    Model theModel) {
        try {
            Patient thePatient = patientService.getPatient(theId);
            theModel.addAttribute("patient", thePatient);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/treatment/list?patientId=" + theId;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
