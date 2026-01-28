package org.vyatsu.ahocorasick.drivers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.vyatsu.ahocorasick.DBConfig;

public class PGDriver {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DBConfig.URL, DBConfig.USER, DBConfig.PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
