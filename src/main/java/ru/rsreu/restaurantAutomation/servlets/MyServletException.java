package ru.rsreu.restaurantAutomation.servlets;

public class MyServletException extends RuntimeException {
    private static final long serialVersionUID = 221L;

    public MyServletException(String message, Exception e) {
        super(message, e);
    }
}
