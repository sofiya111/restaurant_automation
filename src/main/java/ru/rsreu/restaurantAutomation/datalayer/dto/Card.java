package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.sql.Timestamp;

public class Card {
    private String cardNumber;
    private User client;
    private Timestamp validity;

    public Card(String cardNumber, User client, Timestamp validity) {
        this.cardNumber = cardNumber;
        this.client = client;
        this.validity = validity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Timestamp getValidity() {
        return validity;
    }

    public void setValidity(Timestamp validity) {
        this.validity = validity;
    }
}
