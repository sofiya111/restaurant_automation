package ru.rsreu.restaurantAutomation.datalayer.dto;

/**
 * Represents user authorization
 */
public enum Authorization {
    LOGIN(1, "Авторизован"),
    LOGOUT(2, "Неавторизован"),
    NOT_FOUND(3, "Not found");

    private final String name;
    private final int number;

    Authorization(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
