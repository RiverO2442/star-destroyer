package org.rivero.roommanagement.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class DBConnectionManager {
    final DatasourceProperties properties;
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
