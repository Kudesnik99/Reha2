package ru.tsystems.reha.service;

public class ServiceException extends Throwable {
    private ErrorService error;

    public ServiceException(ErrorService error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
    }

    public ServiceException(ErrorService error) {
        this.error = error;
    }

    public ErrorService getError() {
        return error;
    }
}

