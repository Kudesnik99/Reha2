package ru.tsystems.reha.entity;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Entity
@Table(name = "event")
@NamedQueries({
        @NamedQuery(name = "Event.findByTreatment", query = "select ev from Event ev where ev.treatment.treatmentId = :treatmentId")
})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private int eventId;
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) { this.eventId = eventId; }

    @ManyToOne
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id", nullable = false)
    private Treatment treatment;
    public Treatment getTreatmentId() { return treatment; }
    public void setTreatmentId(Treatment treatment) {this.treatment = treatment; }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time")
    private Date dateTime;
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) { this.dateTime = dateTime; }

    @Column(name = "status")
    private String status;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "reason")
    private String reason;
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventId != event.eventId) return false;
        if (dateTime != null ? !dateTime.equals(event.dateTime) : event.dateTime != null) return false;
        if (reason != null ? !reason.equals(event.reason) : event.reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        return result;
    }
}
