package ru.tsystems.reha.rest.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystems.reha.jms.JmsClient;
import ru.tsystems.reha.rest.model.Events;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.exception.ServiceException;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestRequestsHandler {

    private static final Logger LOG = Logger.getLogger(RestRequestsHandler.class);

    @Autowired
    JmsClient jsmClient;

    private final EventService eventService;

    @Autowired
    public RestRequestsHandler(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/add")
    public Events getAddedEvents(@RequestParam("eventIds") List<Long> eventIds){
        return getAddOrUpdateEvents(eventIds);
    }

    @GetMapping("/update")
    public Events getUpdatedEvents(@RequestParam("eventIds")List<Long> eventIds){
        System.out.println(eventIds);
        return getAddOrUpdateEvents(eventIds);
    }

    private Events getAddOrUpdateEvents(List<Long> eventIds) {
        Events result = null;
        try {
            result = eventService.getSomeEventsForPanel(eventIds);
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return result;
    }

    @GetMapping("/all")
    public Events getAllEvents(){
        Events result = null;
        try {
            result = eventService.getAllEventsForPanel();
        } catch (ServiceException e) {
            LOG.warn(e.getError().getMessageForLog(), e);
        }
        return result;
    }
}
