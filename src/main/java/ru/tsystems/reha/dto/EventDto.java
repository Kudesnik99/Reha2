package ru.tsystems.reha.dto;

import ru.tsystems.reha.entity.enums.EventStatus;

public class EventDto {

    private Long eventId;

    private TreatmentDto treatmentDto;

    private String dateTime;

    private EventStatus status;

    private String reason;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public TreatmentDto getTreatmentDto() {
        return treatmentDto;
    }

    public void setTreatmentDto(TreatmentDto treatmentDto) {
        this.treatmentDto = treatmentDto;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
