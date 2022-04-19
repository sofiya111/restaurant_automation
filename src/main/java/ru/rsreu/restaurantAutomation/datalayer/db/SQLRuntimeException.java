package ru.rsreu.restaurantAutomation.datalayer.db;

public class SQLRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 21L;

    public SQLRuntimeException(String message, Exception e) {
        super(message, e);
    }
}
