package ru.tsystems.reha.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.reha.entity.Event;
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
    public String listPatients(Model model) { //}, Authentication authentication) {
        try {
            List<Event> events = eventService.getEvents();
            model.addAttribute("events", events);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
            model.addAttribute("exception", e.getError().getMessage());
        }
        return "list-events";
    }
}
