package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String cardNumber;
    @NotNull
    private User client;
    @NotNull
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
