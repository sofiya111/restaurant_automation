package ru.rsreu.restaurantAutomation.datalayer.db;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager INSTANCE = new DBResourceManager();
    private final ResourceBundle bundle = ResourceBundle.getBundle("db_connection");
    private final ResourceBundle bundleQuery = ResourceBundle.getBundle("queries");

    public static DBResourceManager getInstance() {
        return INSTANCE;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

    public String getQuery(String key) {
        return bundleQuery.getString(key);
    }
}
