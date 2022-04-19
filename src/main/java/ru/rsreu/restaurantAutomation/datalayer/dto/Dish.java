package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.util.Objects;

/**
 * Respresents dish
 */
public class Dish {
    private int id;
    private String name;
    private float price;

    public Dish(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id && Float.compare(dish.price, price) == 0 && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
