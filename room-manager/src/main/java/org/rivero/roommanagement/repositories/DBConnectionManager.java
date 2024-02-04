package org.rivero.roommanagement.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    String hostName = "14.225.205.235:35432";
    String dbName = "room_manager";
    String username = "postgres";
    String password = "P@ssword789";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    String.format("jdbc:postgresql://%s/%s", hostName, dbName),
                    username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
