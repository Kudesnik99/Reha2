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
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Pattern;
import ru.tsystems.reha.entity.Remedy;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;
import ru.tsystems.reha.service.*;

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
            if (patientId > 0) {
                //List<Treatment> treatments = treatmentService.getTreatments();
                List<Treatment> treatments = treatmentService.getTreatmentsByPatientId(patientId);
                Patient patient = patientService.getPatient(patientId);
                model.addAttribute("treatments", treatments);
                model.addAttribute("userDto", authentication.getPrincipal());
                model.addAttribute("patient", patient);
                model.addAttribute("patientId", patientId);
            } else {
                List<Treatment> treatments = treatmentService.getTreatments();
                model.addAttribute("treatments", treatments);
                model.addAttribute("userDto", authentication.getPrincipal()); //userDto);
                model.addAttribute("patientId", patientId);
            }
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
    public String showFormForSave(@RequestParam("patientId") int theId,
                                  Model model) {
        try {
            TreatmentForm treatmentForm = new TreatmentForm();
            treatmentForm.setPatient(treatmentService.getPatientByPatientId(theId));
            treatmentForm.setPatientId(treatmentForm.getPatient().getPatientId());
            model.addAttribute("treatmentForm", treatmentForm);
            initReferenceBooks(model);
//            Treatment treatment = TreatmentFormConverter.toTreatment(treatmentForm);
//            Treatment theTreatment = new Treatment();
//            theTreatment.setPatient(treatmentService.getPatientByPatientId(theId));
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            model.addAttribute("exception", e.getMessage());
        }
        return "treatment-form";
    }

    @PostMapping("/saveTreatment")
    public String saveTreatment(@ModelAttribute("treatmentForm") TreatmentForm treatmentForm) {
        try {
            //theTreatment.getPatient()
            treatmentService.saveTreatment(treatmentForm);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        Patient patient = treatmentForm.getPatient();
        return "redirect:/treatment/list?patientId=" + patient.getPatientId();
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

    @GetMapping("/delete")
    public String deleteTreatment(@RequestParam("treatmentId") int theId) {
        treatmentService.deleteTreatment(theId);
        return "redirect:/treatment/list";
    }

    @GetMapping("/generate")
    public String generateEvents(@RequestParam("treatmentId") int treatmentId, @RequestParam("patientId") int patientId) {
        try {
            treatmentService.generateEvents(treatmentId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "redirect:/treatment/list?patientId=" + patientId;
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
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
    }
}