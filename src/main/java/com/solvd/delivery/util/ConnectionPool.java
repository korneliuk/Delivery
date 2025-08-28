package com.solvd.delivery.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;


public class ConnectionPool {

    private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool instance;

    private static String URL;
    private static String username;
    private static String password;

    private static final Queue<Connection> availableConnections = new LinkedList<>();
    private final Set<Connection> connectionsInUse = new HashSet<>();

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() throws SQLException, IOException {

        ConnectionPool result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ConnectionPool.class) {
            if (instance == null) {
                instance = new ConnectionPool();
                initializeDatabaseAccessData();
            }
            return instance;
        }
    }

    private static void initializeDatabaseAccessData() throws IOException, SQLException {
        Properties p = new Properties();
        InputStream in = ConnectionPool.class
                .getClassLoader().getResourceAsStream("config.properties");
        if (in == null)
            throw new IllegalStateException("database.properties not found");
        p.load(in);
        URL = p.getProperty("database.url");
        username = p.getProperty("database.username");
        password = p.getProperty("database.password");
        int poolSize = Integer.parseInt(p.getProperty("database.pool.size"));

        for (int i = 0; i < poolSize; ++i) {
            availableConnections.add(getNewConnection());
        }
    }

    private static Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(
                URL, username, password);
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection connectionFromAvailable = availableConnections.poll();

        if (connectionFromAvailable == null) {
            connectionFromAvailable = getNewConnection();
        }

        connectionsInUse.add(connectionFromAvailable);
        return connectionFromAvailable;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connectionsInUse.remove(connection)) {
            availableConnections.offer(connection);
        }
    }

    public synchronized void closeAllConnections() throws SQLException {
        for (Connection con : availableConnections) {
            con.close();
        }
        for (Connection con : connectionsInUse) {
            con.close();
        }
    }
}
