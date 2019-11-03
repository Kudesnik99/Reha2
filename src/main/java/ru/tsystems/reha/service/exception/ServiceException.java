package ru.tsystems.reha.service.exception;

public class ServiceException extends Exception {
    private final ServiceError error;

    public ServiceException(ServiceError error, Throwable cause) {
        super(error.getMessage(), cause);
        this.error = error;
    }

    public ServiceException(ServiceError error) {
        this.error = error;
    }

    public ServiceError getError() {
        return error;
    }
}

