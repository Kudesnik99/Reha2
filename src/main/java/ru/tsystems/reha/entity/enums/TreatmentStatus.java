package ru.tsystems.reha.entity.enums;

public enum TreatmentStatus {
    PLANNED("Planned"),
    ASSIGNED("Assigned"),
    PROCESSING("Processing"),
    CANCELED("Canceled"),
    PARTLY_EXECUTED("Partly executed"),
    EXECUTED("Executed");

    private final String statusName;

    TreatmentStatus(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String toString() {
        return this.statusName;
    }
}
