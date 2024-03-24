package org.rivero.roommanager;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class DBConnectionManager {
    final DataSourceProperties properties;

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    properties.getUrl(),
                    properties.getUsername(), properties.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
