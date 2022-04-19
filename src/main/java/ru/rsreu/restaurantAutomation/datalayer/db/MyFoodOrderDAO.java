package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Logger;
import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;
import ru.rsreu.restaurantAutomation.datalayer.dto.FoodOrder;

import java.sql.*;
import java.util.Map;

public class MyFoodOrderDAO implements FoodOrderDAO{
    private static final String FOOD_ORDER_QUERY = "db.query.food_order";
    private static final String FOOD_ORDER_DISH_QUERY = "db.query.food_order_dish";
    private static final String GET_FOOD_ORDER_QUERY = "db.query.get_food_order_id";
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final int CLIENT_COLUMN_NUMBER = 1;
    private static final int PRICE_COLUMN_NUMBER = 2;
    private static final int TIME_COLUMN_NUMBER = 3;
    private static final int FOOD_ORDER_COLUMN_NUMBER = 1;
    private static final int DISH_COLUMN_NUMBER = 2;
    private static final int DISH_COUNT_COLUMN_NUMBER = 3;
    private static final Logger LOGGER = Logger.getLogger(MyFoodOrderDAO.class);
    private final DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final ConnectionPool connectionPool;

    public MyFoodOrderDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void makeFoodOrder(FoodOrder foodOrder) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(FOOD_ORDER_QUERY))) {
            preparedStatement.setInt(CLIENT_COLUMN_NUMBER, foodOrder.getClient().getId());
            preparedStatement.setFloat(PRICE_COLUMN_NUMBER, foodOrder.getPrice());
            Timestamp timestamp = new Timestamp(foodOrder.getOrderTime().getTime());
            preparedStatement.setTimestamp(TIME_COLUMN_NUMBER, new java.sql.Timestamp(timestamp.getTime()));
            preparedStatement.executeUpdate();
            for (Map.Entry<Dish, Integer> entry : foodOrder.getDishes().entrySet()) {
                addFoodOrderDish(getFoodOrderId(), entry.getKey().getId(), entry.getValue());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    private void addFoodOrderDish(int foodOrder, int dish, int dishCount) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(FOOD_ORDER_DISH_QUERY))) {
            preparedStatement.setInt(FOOD_ORDER_COLUMN_NUMBER, foodOrder);
            preparedStatement.setInt(DISH_COLUMN_NUMBER, dish);
            preparedStatement.setInt(DISH_COUNT_COLUMN_NUMBER, dishCount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    private int getFoodOrderId() {
        int id = 0;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_FOOD_ORDER_QUERY))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(FOOD_ORDER_COLUMN_NUMBER);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
        return id;
    }
}
