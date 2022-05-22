package ru.rsreu.restaurantAutomation.datalayer.dto;

import lombok.Getter;
import lombok.Setter;

public class Restaurant {

    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String phoneNumber;

}
