package ru.rsreu.restaurantAutomation.datalayer.db;

import ru.rsreu.restaurantAutomation.datalayer.dto.Reservation;
import ru.rsreu.restaurantAutomation.datalayer.dto.Table;

import java.sql.Timestamp;
import java.util.List;

/**
 * Describes access interface to reservation Data Access Object
 */
public interface ReservationDAO {

    /**
     * Get free tables
     * @param reservationTime reservtion time
     * @return free tables
     */
    List<Table> getFreeTables(Timestamp reservationTime);

    /**
     * Make reservation with food order
     */
    void makeOrder();

    /**
     * Make reservation
     * @param reservation
     */
    void makeReservation(Reservation reservation);

    /**
     * Find tables
     * @param tablesNumber
     * @return found tables
     */
    List<Table> findTables(List<Integer> tablesNumber);

}
