package ru.rsreu.restaurantAutomation.datalayer.db;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String SQLEXCEPTION_MESSAGE = "SQLException in ConnectionPool";
    private static final String CLASS_NOR_FOUND_EXCEPTION_MESSAGE = "Can't find database driver class";
    private static final String INTERRUPTED_EXCEPTION_MESSAGE = "Error connecting to the data source.";
    private static final String CONNECTION_SQLEXCEPTION_MESSAGE = "Connection isn't return to the pool.";
    private static final String RESULTSET_SQLEXCEPTION_MESSAGE = "ResultSet isn't closed.";
    private static final String STATEMENT_SQLEXCEPTION_MESSAGE = "Statement isn't closed.";
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static final ConnectionPool CONNECTION_POOL = new ConnectionPool();
    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    public ConnectionPool() {
        DBResourceManager dbResourseManager = DBResourceManager.getInstance();
        this.driverName = dbResourseManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourseManager.getValue(DBParameter.DB_URL);
        this.user = dbResourseManager.getValue(DBParameter.DB_USER);
        this.password = dbResourseManager.getValue(DBParameter.DB_PASSWORD);
        try {
            this.poolSize = Integer.parseInt(dbResourseManager.getValue(DBParameter.DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public static ConnectionPool getInstance() {
        return CONNECTION_POOL;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, INTERRUPTED_EXCEPTION_MESSAGE, e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void freeConnection() {
        try {
            connectionQueue.add(givenAwayConQueue.take());
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, INTERRUPTED_EXCEPTION_MESSAGE, e);
            Thread.currentThread().interrupt();
        }
    }

    public void initPoolData() throws ConnectionPoolException {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName(driverName);
            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url,user,password);
                PooledConnection pooledConnection = new PooledConnection(
                    connection, this);
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(SQLEXCEPTION_MESSAGE, e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(CLASS_NOR_FOUND_EXCEPTION_MESSAGE, e);
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(givenAwayConQueue);
            closeConnectionsQueue(connectionQueue);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, CONNECTION_SQLEXCEPTION_MESSAGE, e);
        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, CONNECTION_SQLEXCEPTION_MESSAGE);
        }
        try {
            rs.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, RESULTSET_SQLEXCEPTION_MESSAGE);
        }
        try {
            st.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, STATEMENT_SQLEXCEPTION_MESSAGE);
        }
        freeConnection();
    }

    public void closeConnection(Connection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, CONNECTION_SQLEXCEPTION_MESSAGE);
        }
        try {
            st.close();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, STATEMENT_SQLEXCEPTION_MESSAGE);
        }
        freeConnection();
    }

    private void closeConnectionsQueue(BlockingQueue<Connection> queue)
        throws SQLException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    public BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public BlockingQueue<Connection> getGivenAwayConQueue() {
        return givenAwayConQueue;
    }
}