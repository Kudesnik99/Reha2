package ru.tsystems.reha.dao.exception;

public enum DaoError {

    PERSIST_EXCEPTION(1, "A database error has occured."),
    NOTFOUND_EXCEPTION(2, "Requested data doesn't found.");

    private final int id;
    private final String message;

    DaoError(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}

