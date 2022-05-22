package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Logger;
import ru.rsreu.restaurantAutomation.datalayer.dto.Authorization;
import ru.rsreu.restaurantAutomation.datalayer.dto.LockStatus;
import ru.rsreu.restaurantAutomation.datalayer.dto.Role;
import ru.rsreu.restaurantAutomation.datalayer.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyUserDAO  implements UserDAO{
    private static final String GET_CLIENTS_QUERY = "db.query.get_clients";
    private static final String GET_ALL_USERS_QUERY = "db.query.get_users";
    private static final String ADD_USER_QUERY = "db.query.add_user";
    private static final String EDIT_USER_QUERY = "db.query.edit_user";
    private static final String FIND_USER_QUERY = "db.query.find_user";
    private static final String FIND_CLIENT_QUERY = "db.query.find_client";
    private static final String DELETE_USER_QUERY = "db.query.delete_user";
    private static final String LOCK_USER_QUERY = "db.query.lock_user";
    private static final String UNLOCK_USER_QUERY = "db.query.unlock_user";
    private static final String LOGIN_USER_QUERY = "db.query.login_user";
    private static final String LOGOUT_USER_QUERY = "db.query.logout_user";
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final String LOGIN = "Y";
    private static final String LOCK = "Y";
    private static final String UNLOCK = "N";
    private static final int ID_COLUMN_NUMBER = 1;
    private static final int NAME_COLUMN_NUMBER = 2;
    private static final int ROLE_COLUMN_NUMBER = 3;
    private static final int PHONE_COLUMN_NUMBER = 4;
    private static final int EMAIL_COLUMN_NUMBER = 5;
    private static final int LOCK_STATUS_COLUMN_NUMBER = 6;
    private static final int PASSWORD_COLUMN_NUMBER = 8;
    private static final int AUTHORIZATION_COLUMN_NUMBER = 7;
    private static final Logger LOGGER = Logger.getLogger(MyUserDAO.class);
    private final DBResourceManager dbResourseManager = DBResourceManager.getInstance();
    private final ConnectionPool connectionPool;

    /**
     * Constructor
     * @param connectionPool
     */
    public MyUserDAO(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * @param userToLogin user we want to login
     */
    public void login(User userToLogin) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(LOGIN_USER_QUERY))) {
            preparedStatement.setInt(ID_COLUMN_NUMBER, userToLogin.getId());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    /**
     * @param userToLogout user we want to logout
     */
    public void logout(User userToLogout) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(LOGOUT_USER_QUERY))) {
            preparedStatement.setInt(ID_COLUMN_NUMBER, userToLogout.getId());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    /**
     * get list of users
     * @return
     */
    public List<User> getAllUsers() {
        return getUsers(dbResourseManager.getQuery(GET_ALL_USERS_QUERY));
    }

    public List<User> getClients() {
        return getUsers(dbResourseManager.getQuery(GET_CLIENTS_QUERY));
    }

    private List<User> getUsers(String query) {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return users;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(ID_COLUMN_NUMBER);
        String name = resultSet.getString(NAME_COLUMN_NUMBER);
        Role role = Role.values()[resultSet.getInt(ROLE_COLUMN_NUMBER) - 1];
        String phoneNumber = resultSet.getString(PHONE_COLUMN_NUMBER);
        String email = resultSet.getString(EMAIL_COLUMN_NUMBER);
        LockStatus lockStatus = getLockStatusEnum(resultSet.getString(LOCK_STATUS_COLUMN_NUMBER));
        String password = resultSet.getString(PASSWORD_COLUMN_NUMBER);
        Authorization authorization = getAuthorizationEnum(resultSet.getString(AUTHORIZATION_COLUMN_NUMBER));

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

    public void addUser(User user) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(ADD_USER_QUERY))) {
            preparedStatement.setString(NAME_COLUMN_NUMBER - 1, user.getName());
            preparedStatement.setInt(ROLE_COLUMN_NUMBER - 1, user.getRole().getNumber());
            preparedStatement.setString(PHONE_COLUMN_NUMBER - 1, user.getPhoneNumber());
            preparedStatement.setString(EMAIL_COLUMN_NUMBER - 1, user.getEmail());
            preparedStatement.setString(PASSWORD_COLUMN_NUMBER - 2, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void updateUser(User user) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(EDIT_USER_QUERY))) {
            preparedStatement.setString(NAME_COLUMN_NUMBER - 1, user.getName());
            preparedStatement.setInt(ROLE_COLUMN_NUMBER - 1, user.getRole().getNumber());
            preparedStatement.setString(PHONE_COLUMN_NUMBER - 1, user.getPhoneNumber());
            preparedStatement.setString(EMAIL_COLUMN_NUMBER - 1, user.getEmail());
            preparedStatement.setString(LOCK_STATUS_COLUMN_NUMBER - 1,
                    getLockStatusString(user.getLockStatus()));
            preparedStatement.setString(PASSWORD_COLUMN_NUMBER - 1, user.getPassword());
            preparedStatement.setInt(PASSWORD_COLUMN_NUMBER, user.getId());
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    private String getLockStatusString(LockStatus lockStatus) {
        String resultStatus;
        if (Objects.equals(lockStatus.getNumber(), 1)) {
            resultStatus = LOCK;
        } else {
            resultStatus = UNLOCK;
        }

        return resultStatus;
    }

    public void deleteUser(int id) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(DELETE_USER_QUERY))) {
            preparedStatement.setInt(ID_COLUMN_NUMBER, id);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public User findUser(String login) {
        User user = null;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(FIND_USER_QUERY))) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return user;
    }

    public User findClient(String login) {
        User user;
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(FIND_CLIENT_QUERY))) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }

        return user;
    }

    public void lockUser(int id) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(LOCK_USER_QUERY))) {
            preparedStatement.setInt(ID_COLUMN_NUMBER, id);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void unlockUser(int id) {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(dbResourseManager.getQuery(UNLOCK_USER_QUERY))) {
            preparedStatement.setInt(ID_COLUMN_NUMBER, id);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            LOGGER.error(e + SQLEXCEPTION_MESSAGE);
            throw new SQLRuntimeException(SQLEXCEPTION_MESSAGE, e);
        }
    }
}
