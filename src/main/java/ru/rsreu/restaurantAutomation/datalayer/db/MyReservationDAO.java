package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Logger;
import ru.rsreu.restaurantAutomation.datalayer.dto.Reservation;
import ru.rsreu.restaurantAutomation.datalayer.dto.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyReservationDAO implements ReservationDAO{
    private static final String GET_FREE_TABLES_QUERY = "db.query.get_free_tables";
    private static final String FIND_TABLE_QUERY = "db.query.find_table";
    private static final String RESERVATION_QUERY = "db.query.reservation";
    private static final String RESERVATION_TABLE_QUERY = "db.query.reservation_table";
    private static final String RESERVATION_FOOD_ORDER_TABLE_QUERY = "db.query.reservation_food_order";
    private static final String GET_RESERVATION_QUERY = "db.query.get_reservation_id";
    private static final String GET_FOOD_ORDER_QUERY = "db.query.get_food_order_id";
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final int TIME_START_NUMBER = 1;
    private static final int TIME_END_NUMBER = 2;
    private static final int ID_COLUMN_NUMBER = 1;
    private static final int TABLE_NUMBER_COLUMN_NUMBER = 2;
    private static final int SEATS_NUMBER_COLUMN_NUMBER = 3;
    private static final int CLIENT_COLUMN_NUMBER = 1;
    private static final int TIME_COLUMN_NUMBER = 2;
    private static final int PERSON_NUMBER_COLUMN_NUMBER = 3;
    private static final int RESERVATION_COLUMN_NUMBER = 1;
    private static final int FOOD_ORDER_COLUMN_NUMBER = 2;
    private static final int TABLE_COLUMN_NUMBER = 2;
    private static final int THREE_HOURS = 1000 * 60 * 60 * 3;
    private static final Logger LOGGER = Logger.getLogger(MyReservationDAO.class);
    private final DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final ConnectionPool connectionPool;

    public MyReservationDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Table> getFreeTables(Timestamp reservationTime) {
        List<Table> tables = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_FREE_TABLES_QUERY))) {
            preparedStatement.setString(TIME_START_NUMBER, reservationTime.toString());
            Timestamp reservationTimeEnd = new Timestamp(reservationTime.getTime() + THREE_HOURS);
            preparedStatement.setString(TIME_END_NUMBER, reservationTimeEnd.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tables.add(getTable(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return tables;
    }

    private Table getTable(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN_NUMBER);
        int tableNumber = resultSet.getInt(TABLE_NUMBER_COLUMN_NUMBER);
        int seatsNumber = resultSet.getInt(SEATS_NUMBER_COLUMN_NUMBER);

        return new Table(id, tableNumber, seatsNumber);
    }

    public void makeOrder() {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(RESERVATION_FOOD_ORDER_TABLE_QUERY))) {
            preparedStatement.setInt(RESERVATION_COLUMN_NUMBER, getReservationId());
            preparedStatement.setInt(FOOD_ORDER_COLUMN_NUMBER, getFoodOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void makeReservation(Reservation reservation) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(RESERVATION_QUERY))) {
            preparedStatement.setInt(CLIENT_COLUMN_NUMBER, reservation.getClient().getId());
            Timestamp reservationTime = new Timestamp(reservation.getReservationTime().getTime() + THREE_HOURS);
            preparedStatement.setTimestamp(TIME_COLUMN_NUMBER, new java.sql.Timestamp(reservationTime.getTime()));
            preparedStatement.setInt(PERSON_NUMBER_COLUMN_NUMBER, reservation.getPersonNumber());
            preparedStatement.executeUpdate();
            for (Table table : reservation.getTables()) {
                addReservationTable(getReservationId(), table.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    private int getReservationId() {
        int id = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_RESERVATION_QUERY))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(RESERVATION_COLUMN_NUMBER);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
        return id;
    }

    private int getFoodOrderId() {
        int id = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_FOOD_ORDER_QUERY))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(RESERVATION_COLUMN_NUMBER);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
        return id;
    }

    private void addReservationTable(int reservation, int table) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(RESERVATION_TABLE_QUERY))) {
            preparedStatement.setInt(RESERVATION_COLUMN_NUMBER, reservation);
            preparedStatement.setInt(TABLE_COLUMN_NUMBER, table);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public List<Table> findTables(List<Integer> tablesNumber) {
        List<Table> tables = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(FIND_TABLE_QUERY))) {
            for (Integer table : tablesNumber) {
                preparedStatement.setInt(1, table);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    tables.add(getTable(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return tables;
    }
}
