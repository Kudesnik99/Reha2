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
import ru.tsystems.reha.service.PatternService;
import ru.tsystems.reha.service.RemedyService;
import ru.tsystems.reha.service.ServiceException;
import ru.tsystems.reha.service.TreatmentService;

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

    @GetMapping("/list")
    public String listTreatments(@RequestParam("patientId") int theId, Model model, Authentication authentication) {
        try {
            if (theId > 0) {
                List<Treatment> treatments = treatmentService.getTreatments();
                //List<Treatment> treatments = treatmentService.getTreatmentsByPatientId(theId);
                model.addAttribute("treatments", treatments);
                model.addAttribute("userDto", authentication.getPrincipal());
            } else {
                List<Treatment> treatments = treatmentService.getTreatments();
                model.addAttribute("treatments", treatments);
                model.addAttribute("userDto", authentication.getPrincipal()); //userDto);
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
                                  Model theModel) {
        try {
            TreatmentForm treatmentForm = new TreatmentForm();
            treatmentForm.setPatientId(theId);
            treatmentForm.setPatient(treatmentService.getPatientByPatientId(theId));
            List<Remedy> remedies = remedyService.getRemedies();
            List<Pattern> patterns = patternService.getPatterns();
            theModel.addAttribute("treatmentForm", treatmentForm);
            theModel.addAttribute("remedies", remedies);
            theModel.addAttribute("patterns", patterns);
//            Treatment treatment = TreatmentFormConverter.toTreatment(treatmentForm);
//            Treatment theTreatment = new Treatment();
//            theTreatment.setPatient(treatmentService.getPatientByPatientId(theId));
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            theModel.addAttribute("exception", e.getError().getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            theModel.addAttribute("exception", e.getMessage());
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
            Treatment theTreatment = treatmentService.getTreatment(theId);
            model.addAttribute("treatment", theTreatment);
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
    public String generateEvents(@RequestParam("treatmentId") int theId) {
        try {
            treatmentService.generateEvents(theId);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "redirect:/treatment/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}