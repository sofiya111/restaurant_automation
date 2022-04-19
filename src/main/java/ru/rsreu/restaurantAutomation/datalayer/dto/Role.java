package ru.rsreu.restaurantAutomation.datalayer.dto;

/**
 * Represents user role
 */
public enum Role {
    ADMIN(1, "Администратор"),
    MODER(2, "Модератор"),
    EMPLOYEE(3, "Работник"),
    USER(4, "Клиент"),
    NOT_FOUND(5, "Not found");


    private final String name;
    private final int number;

    Role(int number, String name) {
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
