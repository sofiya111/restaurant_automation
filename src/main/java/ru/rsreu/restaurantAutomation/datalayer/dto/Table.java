package ru.rsreu.restaurantAutomation.datalayer.dto;

/**
 * Represents table for reservation
 */
public class Table {
    private int id;
    private int tableNumber;
    private int seatsNumber;

    public Table(int id, int tableNumber, int seatsNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.seatsNumber = seatsNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
}
