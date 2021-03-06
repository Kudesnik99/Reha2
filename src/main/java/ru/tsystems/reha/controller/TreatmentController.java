package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.*;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;
import ru.tsystems.reha.service.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/treatment")
public class TreatmentController {
    private static final Logger LOG = Logger.getLogger(TreatmentController.class);

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private RemedyService remedyService;

    @Autowired
    private PatternService patternService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public String listTreatments(@RequestParam("patientId") int patientId, Model model, Authentication authentication) {
        try {
            List<Treatment> treatments;
            if (patientId > 0) {
                treatments = treatmentService.getTreatmentsByPatientId(patientId);
                Patient patient = patientService.getPatient(patientId);
                model.addAttribute("patient", patient);
            } else {
                treatments = treatmentService.getTreatments();
            }
            model.addAttribute("treatments", treatments);
            model.addAttribute("userDto", authentication.getPrincipal());
            model.addAttribute("patientId", patientId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            model.addAttribute("exception", e.getMessage());
        }
        return "list-treatments";
    }

    @GetMapping("/showForm")
    public String showFormForSave(@RequestParam("patientId") int patientId,
                                  Model model) {
        try {
            TreatmentForm treatmentForm = new TreatmentForm();
            treatmentForm.setPatient(patientService.getPatient(patientId));
            treatmentForm.setPatientId(treatmentForm.getPatient().getPatientId());
            model.addAttribute("treatmentForm", treatmentForm);
            initReferenceBooks(model);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            model.addAttribute("exception", e.getMessage());
        }
        return "treatment-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("treatmentId") int theId,
                                    Model model) {
        try {
            Treatment treatment = treatmentService.getTreatment(theId);
            TreatmentForm treatmentForm = TreatmentFormConverter.toTreatmentForm(treatment);
            model.addAttribute("treatmentForm", treatmentForm);
            initReferenceBooks(model);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "treatment-form";
    }


    @PostMapping("/saveTreatment")
    public String saveTreatment(@ModelAttribute("treatmentForm") TreatmentForm treatmentForm) {
        try {
            //theTreatment.getPatient()
            treatmentForm.setPatient(patientService.getPatient(treatmentForm.getPatientId()));
            treatmentService.saveTreatment(treatmentForm);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Patient patient = treatmentForm.getPatient();
        return "redirect:/treatment/list?patientId=" + patient.getPatientId();
    }

    @GetMapping("/delete")
    public String deleteTreatment(@RequestParam("treatmentId") int theId) {
        try {
            treatmentService.deleteTreatment(theId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/treatment/list?treatmentId=0";
    }

    @GetMapping("/generate")
    public String generateEvents(@RequestParam("treatmentId") int treatmentId) {
        try {
            treatmentService.generateEvents(treatmentId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "redirect:/event/list?treatmentId=" + treatmentId;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private void initReferenceBooks(Model model) {
        try {
            List<Remedy> remedies = remedyService.getRemedies();
            List<Pattern> patterns = patternService.getPatterns();
            model.addAttribute("remedies", remedies);
            model.addAttribute("patterns", patterns);
            model.addAttribute("statuses", TreatmentStatus.values());
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
    }

//    public String dateTimeFormat(Date date) {
//        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        return dateFormat.format(date);
//    }

}