package ru.tsystems.reha.service;

public enum ErrorService {
    PERSIST_EXCEPTION(1, "A database error has occurred.", "database error has occurred"),
    DUPLICATE_TREATMENT(2, "This treatment has already exist.", "treatment has already exist"),
    DUPLICATE_EVENT(3, "This event has already exist.", "event already exist"),
    DUPLICATE_USER(4, "Sorry, a user with the same email address already exists", "user with the same email address already exists"),
    TREATMENT_NOT_EXIST(5, "The treatment does not exist.", "the treatment does not exist"),
    EVENT_NOT_EXIST(6, "The event does not exist.", "the event does not exist");

    private final int id;
    private final String message;
    private final String messageForLog;

    ErrorService(int id, String message, String messageForLog) {
        this.id = id;
        this.message = message;
        this.messageForLog = messageForLog;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    public String getMessageForLog() {
        return messageForLog + " ";
    }
}