package ru.rsreu.restaurantAutomation.datalayer.db;

/**
 * Describes Data Access Object Factory
 */
public class DAOFactory {

    private final ConnectionPool connectionPool = new ConnectionPool();
    private static DAOFactory instance = new DAOFactory();

    private DAOFactory() {
        try {
            connectionPool.initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public MyUserDAO getUserDAO() {
        return new MyUserDAO(connectionPool);
    }

    public MyMenuDAO getMenuDAO() {
        return new MyMenuDAO(connectionPool);
    }

    public MyReservationDAO getReservationDAO() {
        return new MyReservationDAO(connectionPool);
    }

    public MyFoodOrderDAO getFoodOrderDAO() {
        return new MyFoodOrderDAO(connectionPool);
    }
    public MyOrderDAO getOrderDAO() {
        return new MyOrderDAO(connectionPool);
    }
}
