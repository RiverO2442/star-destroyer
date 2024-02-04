package org.rivero.roommanagement.repositories;

import lombok.SneakyThrows;
import org.rivero.roommanagement.dtos.UserInfo;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.request.UserUpdateRequest;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    public List<User> getList(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user_tbl");
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("passwordHash");
                int balance = rs.getInt("balance");
                int debt = rs.getInt("debt");
                users.add(new User(id, username, password, 0, balance, debt));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteOne(Connection connection, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user_tbl WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOne(Connection connection, UserUpdateRequest userUpdateRequest, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_tbl SET passwordhash = ? WHERE  id = ?");
            preparedStatement.setString(1, DigestUtils.md5DigestAsHex(userUpdateRequest.passwordHash().getBytes(StandardCharsets.UTF_8)));
            preparedStatement.setString(2, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void increaseUserDebt(Connection connection, int amount, String id, UserInfo userInfo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_tbl SET debt = debt + ? WHERE id = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void increaseUserBalance(Connection connection, int amount, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_tbl SET balance = balance + ? WHERE id = ?");
            preparedStatement.setInt(1, amount);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getOne(Connection connection, String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_tbl WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs;
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("id");
                String username = rs.getString("username");
                String password = rs.getString("passwordHash");
                int balance = rs.getInt("balance");
                int debt = rs.getInt("debt");
                return new User(id, username, password, 0, balance, debt);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public Optional<User> getByUsername(Connection connection, String username) {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("SELECT * FROM user_tbl WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet rs;
        rs = preparedStatement.executeQuery();
        if (rs.next()) {
            String userId = rs.getString("id");
            String password = rs.getString("passwordHash");
            int balance = rs.getInt("balance");
            int debt = rs.getInt("debt");
            return Optional.of(new User(userId, username, password, 0, balance, debt));
        }
        return Optional.empty();
    }

    public void insert(Connection connection, User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user_tbl VALUES (?, ?, ?)");
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, DigestUtils.md5DigestAsHex(user.getPasswordHash().getBytes(StandardCharsets.UTF_8)));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getPasswordHash(Connection connection, String username) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT  * FROM user_tbl WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("passwordHash"));
                return rs.getString("passwordHash");
            }
            return "";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}