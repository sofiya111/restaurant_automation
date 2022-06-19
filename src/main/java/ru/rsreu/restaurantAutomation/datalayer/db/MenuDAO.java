package ru.rsreu.restaurantAutomation.datalayer.db;

import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;

import java.util.List;

/**
 * Describes access interface to menu Data Access Object
 */
public interface MenuDAO {

    /**
     * Get menu
     * @return dishes in menu
     */
    List<Dish> getMenu();

    /**
     * Add dish
     * @param dish dish to add
     */
    void addDish(Dish dish,int establishment);

    /**
     * Delete dish
     * @param dishName dish to delete
     */
    void deleteDish(String dishName);

    /**
     * Edit dish
     * @param dish dish to edit
     */
    void editDish(Dish dish);

}
