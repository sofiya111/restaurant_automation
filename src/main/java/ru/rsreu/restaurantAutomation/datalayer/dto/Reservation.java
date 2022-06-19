package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Represents reservation
 */
public class Reservation {
    /**
     * id
     */
    private int id;
    /**
     * client
     */
    private User client;
    /**
     * reservation time
     */
    private Timestamp reservationTime;
    /**
     * person number
     */
    private int personNumber;
    /**
     * tables for reservation
     */
    private List<Table> tables;
    /**
     * establishment
     */
    private String establishment;

    public Reservation(User client, Timestamp reservationTime, int personNumber, List<Table> tables) {
        this.client = client;
        this.reservationTime = reservationTime;
        this.personNumber = personNumber;
        this.tables = tables;
    }

    public Reservation(User client, Timestamp reservationTime, int personNumber, List<Table> tables, String establishment) {
        this.client = client;
        this.reservationTime = reservationTime;
        this.personNumber = personNumber;
        this.tables = tables;
        this.establishment = establishment;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstablishment() {
        return establishment;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }
}
