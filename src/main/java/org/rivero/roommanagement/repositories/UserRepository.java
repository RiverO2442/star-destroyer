package org.rivero.roommanagement.repositories;

import org.apache.commons.lang3.RandomStringUtils;
import org.rivero.roommanagement.entities.User;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.UUID;

public class UserRepository {
    public ResultSet getList(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user_tbl");
        return rs;
    }

    public ResultSet getOne(Connection connection, String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_tbl WHERE id = ?");
        preparedStatement.setString(1, id);
        ResultSet rs;
        rs = preparedStatement.executeQuery();
        return rs;
    }

    public void insert(Connection connection, User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_tbl VALUES (?, ?, ?)");
        preparedStatement.setString(1, UUID.randomUUID().toString());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, DigestUtils.md5DigestAsHex(user.getPasswordHash().getBytes(StandardCharsets.UTF_8)));
        preparedStatement.execute();
    }

    public String getPasswordHash(Connection connection, String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM user_tbl WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getString("passwordHash"));
            return rs.getString("passwordHash");
        }
        return "";
    }
}
