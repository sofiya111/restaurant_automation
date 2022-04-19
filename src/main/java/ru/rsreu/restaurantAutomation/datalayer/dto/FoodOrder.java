package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Respresents food order
 */
public class FoodOrder {
    /**
     * client
     */
    private User client;
    /**
     * order price
     */
    private float price;
    /**
     * order time
     */
    private Timestamp orderTime;
    /**
     * dished for order
     */
    private Map<Dish, Integer> dishes;

    public FoodOrder(User client, float price, Timestamp orderTime, Map<Dish, Integer> dishes) {
        this.client = client;
        this.price = price;
        this.orderTime = orderTime;
        this.dishes = dishes;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public Map<Dish, Integer> getDishes() {
        return dishes;
    }

    public void setDishes(Map<Dish, Integer> dishes) {
        this.dishes = dishes;
    }
}
