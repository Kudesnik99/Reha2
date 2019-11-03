package ru.tsystems.reha.entity.enums;

public enum EventStatus {

    PLANNED("Planned"),
    CANCELED("Canceled"),
    EXECUTED("Executed");

    private final String statusName;

    EventStatus(String statusName) {
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

