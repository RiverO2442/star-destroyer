package org.rivero.roommanagement.services;

import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.UserRepository;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    UserRepository userRepository = new UserRepository();
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    public String login(User user) throws SQLException {
        if (DigestUtils.md5DigestAsHex(user.getPasswordHash().getBytes(StandardCharsets.UTF_8)).equals(userRepository.getPasswordHash(connection, user.getName())))
            return DigestUtils.md5DigestAsHex("Login Completed".getBytes(StandardCharsets.UTF_8));
        else return "Login Failed";
    }

    public User getUserById(String id) throws SQLException {
        ResultSet rs = userRepository.getOne(connection, id);
        if (rs.next()){
            String userId = rs.getString("id");
            String username = rs.getString("username");
            String password = rs.getString("passwordHash");
            return  new User(userId, username, password, 0);
        }
        else return null;
    }
    public List<User> getAllUser() throws SQLException {
        ResultSet rs = userRepository.getList(connection);
        List<User> users = new ArrayList<>();
        while (rs.next()){
            String id = rs.getString("id");
            String username = rs.getString("username");
            String password = rs.getString("passwordHash");
            users.add(new User(id, username, password, 0));
        }
        return users;
    }

    public String register(User user) throws SQLException {
        if (userRepository.getPasswordHash(connection, user.getName()).isEmpty()) {
            userRepository.insert(connection, user);
            return "New User Registered";
        } else return "Existed User";
    }
}
