package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Logger;
import ru.rsreu.restaurantAutomation.datalayer.dto.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyMenuDAO implements MenuDAO{
    private static final String GET_MENU_QUERY = "db.query.get_menu";
    private static final String ADD_DISH_QUERY = "db.query.add_dish";
    private static final String DELETE_DISH_QUERY = "db.query.delete_dish";
    private static final String EDIT_DISH_QUERY = "db.query.edit_menu";
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final int ID_COLUMN_NUMBER = 1;
    private static final int NAME_COLUMN_NUMBER = 2;
    private static final int PRICE_COLUMN_NUMBER = 3;
    private static final Logger LOGGER = Logger.getLogger(MyMenuDAO.class);
    private final DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final ConnectionPool connectionPool;

    public MyMenuDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public List<Dish> getMenu() {
        List<Dish> dishes = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(GET_MENU_QUERY))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dishes.add(getDish(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return dishes;
    }

    private Dish getDish(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN_NUMBER);
        String name = resultSet.getString(NAME_COLUMN_NUMBER);
        float price = resultSet.getInt(PRICE_COLUMN_NUMBER);

        return new Dish(id, name, price);
    }

    public void addDish(Dish dish) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(ADD_DISH_QUERY))) {
            preparedStatement.setString(NAME_COLUMN_NUMBER - 1, dish.getName());
            preparedStatement.setFloat(PRICE_COLUMN_NUMBER - 1, dish.getPrice());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void deleteDish(String dishName) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(DELETE_DISH_QUERY))) {
            preparedStatement.setString(NAME_COLUMN_NUMBER - 1, dishName);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void editDish(Dish dish) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(EDIT_DISH_QUERY))) {
            preparedStatement.setString(NAME_COLUMN_NUMBER - 1, dish.getName());
            preparedStatement.setFloat(PRICE_COLUMN_NUMBER - 1, dish.getPrice());
            preparedStatement.setInt(PRICE_COLUMN_NUMBER, dish.getId());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }
}
