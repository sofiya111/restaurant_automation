package ru.rsreu.restaurantAutomation.datalayer.db;

import ru.rsreu.restaurantAutomation.datalayer.dto.FoodOrder;

/**
 * Describes access interface to food order Data Access Object
 */
public interface FoodOrderDAO {

    /**
     * Make food order
     *
     * @param foodOrder
     */
    void makeFoodOrder(FoodOrder foodOrder, int establishment);
}
