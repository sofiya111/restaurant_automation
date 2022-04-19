package ru.rsreu.restaurantAutomation.datalayer.db;

import ru.rsreu.restaurantAutomation.datalayer.dto.User;

import java.util.List;

/**
 * Describes access interface to user Data Access Object
 */
public interface UserDAO {

    /**
     * Login user
     * @param userToLogin user we want to login
     */
    void login(User userToLogin);

    /**
     * Logout user
     * @param userToLogout user we want to logout
     */
    void logout(User userToLogout);

    /**
     * Get all users
     * @return list of users
     */
    List<User> getAllUsers();

    /**
     * Get all clients
     * @return list of users
     */
    List<User> getClients();

    /**
     * Add user
     * @param user user to add
     */
    void addUser(User user);

    /**
     * Update personal data of user
     * @param user user to edit
     */
    void updateUser(User user);

    /**
     * Delete user
     * @param id
     */
    void deleteUser(int id);

    /**
     * Find user
     * @param login login user to find
     * @return found user
     */
    User findUser(String login);

    /**
     * Find client
     * @param login login client to find
     * @return found client
     */
    User findClient(String login);

    /**
     * Lock user
     * @param id id locked user
     */
    void lockUser(int id);

    /**
     * Unlock user
     * @param id id unlocked user
     */
    void unlockUser(int id);
}
