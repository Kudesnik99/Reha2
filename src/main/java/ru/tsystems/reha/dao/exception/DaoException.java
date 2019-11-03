package ru.tsystems.reha.dao.exception;

public class DaoException extends Exception {

    public DaoException(DaoError error, Throwable cause) {
        super(error.getMessage(), cause);
    }

}

