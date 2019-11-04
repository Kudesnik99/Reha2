package ru.tsystems.reha.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.dto.PatientDto;
import ru.tsystems.reha.dto.PatternDto;
import ru.tsystems.reha.dto.UserDto;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.exception.ServiceException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private static final Logger LOG = Logger.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @GetMapping("/list")
    public String listPatients(@RequestParam("treatmentId") int treatmentId, Model model, Authentication authentication) { //}, Authentication authentication) {
        try {
            List<Event> events;

            if (treatmentId > 0) {
                events = eventService.getEventsByTreatmentId(treatmentId);
                Patient patient = eventService.getPatientByTreatmentId(treatmentId);
                model.addAttribute("patient", patient);
            }
            else
                events = eventService.getEvents();
            model.addAttribute("userDto", authentication.getPrincipal());
            model.addAttribute("events", eventsDto);
            model.addAttribute("treatmentId", treatmentId);
            model.addAttribute("statuses", EventStatus.values());

        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        }
        return "list-events";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("eventId") Long id, Model model) {
        try {
            EventDto event = eventService.getEvent(id);
            model.addAttribute("eventDto", event);
            model.addAttribute("statuses", EventStatus.values());
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "event-form";
    }

    @PostMapping("/updateEvent")
    public String updateEvent(@ModelAttribute("event") EventDto eventDto) {
        try {
            eventService.saveEvent(eventDto);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return "redirect:/event/list";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
