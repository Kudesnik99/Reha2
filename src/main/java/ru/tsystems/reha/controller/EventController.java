package ru.tsystems.reha.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Patient;
import ru.tsystems.reha.service.EventService;
import ru.tsystems.reha.service.ServiceException;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private static final Logger LOG = Logger.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/list")
    public String listPatients(@RequestParam("treatmentId") int treatmentId, Model model) { //}, Authentication authentication) {
        try {
            List<Event> events;

            if (treatmentId > 0) {
                events = eventService.getEventsByTreatmentId(treatmentId);
                Patient patient = eventService.getPatientByTreatmentId(treatmentId);
                model.addAttribute("patient", patient);
            }
            else
                events = eventService.getEvents();
            model.addAttribute("events", events);
            model.addAttribute("treatmentId", treatmentId);

        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        }
        return "list-events";
    }
}
