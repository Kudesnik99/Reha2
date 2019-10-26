package ru.tsystems.reha.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.model.TreatmentForm;
import ru.tsystems.reha.model.TreatmentFormConverter;
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

    @GetMapping("/list")
    public String listTreatments(@RequestParam("patientId") int theId, Model theModel) {
        try {
            if (theId > 0) {
                List<Treatment> theTreatments = treatmentService.getTreatments();
                theModel.addAttribute("treatments", theTreatments);
                //ToDo treatmentService.getTreatmentsByPatientId(theId);
            }
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            theModel.addAttribute("exception", e.getError().getMessage());
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            theModel.addAttribute("exception", e.getMessage());
        }
        return "list-treatments";
    }

    @GetMapping("/showForm")
    public String showTreatment(@RequestParam("patientId") int theId,
                                Model theModel) {
        try {
            TreatmentForm treatmentForm = new TreatmentForm();
            treatmentForm.setPatientId(theId);
            treatmentForm.setPatient(treatmentService.getPatientByPatientId(theId));
            theModel.addAttribute("treatmentForm", treatmentForm);
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

    //Andrey question!!!
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
                                    Model theModel) {
        Treatment theTreatment = treatmentService.getTreatment(theId);
        theModel.addAttribute("treatment", theTreatment);
        return "treatment-form";
    }

    @GetMapping("/delete")
    public String deleteTreatment(@RequestParam("treatmentId") int theId) {
        treatmentService.deleteTreatment(theId);
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