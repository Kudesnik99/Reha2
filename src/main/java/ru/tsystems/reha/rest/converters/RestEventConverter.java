package ru.tsystems.reha.rest.converters;

import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.rest.model.Event;
import ru.tsystems.reha.rest.model.Events;
import ru.tsystems.reha.rest.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class RestEventConverter {

    private RestEventConverter() {
    }

    public static Event prepareRestEvent(EventDto eventDto) {
        ru.tsystems.reha.rest.model.Event oneRestEvent = new ru.tsystems.reha.rest.model.Event();
        oneRestEvent.setEventId(eventDto.getEventId());
        oneRestEvent.setTime(eventDto.getDateTime());

        Patient restPatient = new Patient();
        restPatient.setLastName(eventDto.getTreatmentDto().getPatientDto().getLastName());
        restPatient.setFirstName(eventDto.getTreatmentDto().getPatientDto().getFirstName());
        oneRestEvent.setPatient(restPatient);

        oneRestEvent.setRemedy(eventDto.getTreatmentDto().getRemedyDto().getName());
        oneRestEvent.setDetails(eventDto.getTreatmentDto().getDescription());
        oneRestEvent.setStatus(eventDto.getStatus().getStatusName());
        oneRestEvent.setDose(eventDto.getTreatmentDto().getDose().toString());
        oneRestEvent.setUnit(eventDto.getTreatmentDto().getRemedyDto().getUnit());

        return oneRestEvent;
    }

    public static Events prepareRestEvents(List<EventDto> eventsDto) {
        ru.tsystems.reha.rest.model.Events eventsRest = new ru.tsystems.reha.rest.model.Events();
        eventsRest.setEventList(new ArrayList<>());

        for (EventDto oneEvent : eventsDto) {
            ru.tsystems.reha.rest.model.Event oneRestEvent = RestEventConverter.prepareRestEvent(oneEvent);
            eventsRest.getEventList().add(oneRestEvent);
        }
        return eventsRest;
    }
}
