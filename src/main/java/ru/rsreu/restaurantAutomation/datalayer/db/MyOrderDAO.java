package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Logger;
import ru.rsreu.restaurantAutomation.datalayer.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyOrderDAO implements OrderDAO {
    private static final String GET_ALL_RESERVATIONS = "db.query.get_all_reservations";
    private static final String GET_ALL_FOOD_ORDERS = "db.query.get_all_food_orders";
    private static final String GET_ALL_RESERVATION_FOOD_ORDERS = "db.query.get_all_reservation_food_orders";
    private static final String GET_USER_RESERVATIONS = "db.query.get_user_reservations";
    private static final String GET_TABLES = "db.query.get_tables";
    private static final String GET_ESTABLISHMENT = "db.query.get_establishment";
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final int ORDER_COLUMN_NUMBER = 1;
    private static final int ID_COLUMN_NAME = 6;
    private static final int ID_TABLE_COLUMN_NAME = 1;
    private static final int TABLE_NUMBER_COLUMN_NAME = 2;
    private static final int SEATS_NUMBER_COLUMN_NAME = 3;
    private static final int TIME_COLUMN_NAME = 4;
    private static final int PERSON_NUMBER_COLUMN_NAME = 5;
    private static final int NAME_COLUMN_NAME = 7;
    private static final int ROLE_COLUMN_NAME = 8;
    private static final int PHONE_COLUMN_NAME = 9;
    private static final int EMAIL_COLUMN_NAME = 10;
    private static final int LOCK_STATUS_COLUMN_NAME = 11;
    private static final int PASSWORD_COLUMN_NAME = 13;
    private static final int AUTHORIZATION_COLUMN_NAME = 12;
    private static final String LOGIN = "Y";
    private static final String LOCK = "Y";
    private static final Logger LOGGER = Logger.getLogger(MyOrderDAO.class);
    private final DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final ConnectionPool connectionPool;

    public MyOrderDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Reservation> getReservationsEmployee(int user) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_ALL_RESERVATIONS))) {
            preparedStatement.setInt(ORDER_COLUMN_NUMBER, getEstablishment(user));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(getReservation(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return reservations;
    }

    public List<Reservation> getReservationsUser(int user) {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_USER_RESERVATIONS))) {
            preparedStatement.setInt(ORDER_COLUMN_NUMBER, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reservations.add(getUserReservation(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return reservations;
    }

    private Reservation getUserReservation(ResultSet resultSet) throws SQLException {
        Timestamp time = resultSet.getTimestamp(TIME_COLUMN_NAME-1);
        String establishment = resultSet.getString(2);
        int personNumber = resultSet.getInt(PERSON_NUMBER_COLUMN_NAME-1);
        List<Table> tables = getTables(resultSet.getInt(ORDER_COLUMN_NUMBER));
        Reservation reservation = new Reservation(User.NULL_USER, time, personNumber, tables,establishment);
        reservation.setId(resultSet.getInt(ID_TABLE_COLUMN_NAME));

        return reservation;
    }

    public int getEstablishment(int user) {
        int id = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_ESTABLISHMENT))) {
            preparedStatement.setInt(ORDER_COLUMN_NUMBER, user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(ORDER_COLUMN_NUMBER);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return id;
    }

    private Reservation getReservation(ResultSet resultSet) throws SQLException {
        User client = getUser(resultSet);
        Timestamp time = resultSet.getTimestamp(TIME_COLUMN_NAME);
        int personNumber = resultSet.getInt(PERSON_NUMBER_COLUMN_NAME);
        List<Table> tables = getTables(resultSet.getInt(ORDER_COLUMN_NUMBER));
        Reservation reservation = new Reservation(client, time, personNumber, tables);
        reservation.setId(resultSet.getInt(ID_TABLE_COLUMN_NAME));

        return reservation;
    }

    public List<Table> getTables(int reservation) {
        List<Table> tables = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_TABLES))) {
            preparedStatement.setInt(ORDER_COLUMN_NUMBER, reservation);
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
        int id = resultSet.getInt(ID_TABLE_COLUMN_NAME);
        int tableNumber = resultSet.getInt(TABLE_NUMBER_COLUMN_NAME);
        int seatsNumber = resultSet.getInt(SEATS_NUMBER_COLUMN_NAME);

        return new Table(id, tableNumber, seatsNumber);
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN_NAME);
        String name = resultSet.getString(NAME_COLUMN_NAME);
        Role role = Role.values()[resultSet.getInt(ROLE_COLUMN_NAME) - 1];
        String phoneNumber = resultSet.getString(PHONE_COLUMN_NAME);
        String email = resultSet.getString(EMAIL_COLUMN_NAME);
        LockStatus lockStatus = getLockStatusEnum(resultSet.getString(LOCK_STATUS_COLUMN_NAME));
        String password = resultSet.getString(PASSWORD_COLUMN_NAME);
        Authorization authorization = getAuthorizationEnum(resultSet.getString(AUTHORIZATION_COLUMN_NAME));

        return new User(id, name, role, phoneNumber, email, lockStatus, password, authorization);
    }

    private LockStatus getLockStatusEnum(String lockStatus) {
        LockStatus resultStatus;
        if (Objects.equals(lockStatus, LOCK)) {
            resultStatus = LockStatus.LOCK;
        } else {
            resultStatus = LockStatus.UNLOCK;
        }

        return resultStatus;
    }

    private Authorization getAuthorizationEnum(String authorization) {
        Authorization resultStatus;
        if (Objects.equals(authorization, LOGIN)) {
            resultStatus = Authorization.LOGIN;
        } else {
            resultStatus = Authorization.LOGOUT;
        }

        return resultStatus;
    }

}
