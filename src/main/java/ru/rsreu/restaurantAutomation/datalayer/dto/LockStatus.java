package ru.rsreu.restaurantAutomation.datalayer.dto;

/**
 * Represents user lock status
 */
public enum LockStatus {
    LOCK(1, "Заблокирован"),
    UNLOCK(2, "Незаблокирован"),
    NOT_FOUND(3, "Not found");

    private final String name;
    private final int number;

    LockStatus(int number, String name) {
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
