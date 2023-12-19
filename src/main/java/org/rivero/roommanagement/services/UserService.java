package org.rivero.roommanagement.services;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.DBConnectionManager;
import org.rivero.roommanagement.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.List;
@RequiredArgsConstructor
@Service
public class UserService {
    private  final UserRepository userRepository = new UserRepository();
    DBConnectionManager dbConnectionManager = new DBConnectionManager();
    Connection connection = dbConnectionManager.connect();

    public String login(User user) {
        if (DigestUtils.md5DigestAsHex(user.getPasswordHash().getBytes(StandardCharsets.UTF_8)).equals(userRepository.getPasswordHash(connection, user.getName())))
            return DigestUtils.md5DigestAsHex(user.getName().getBytes(StandardCharsets.UTF_8));
        else throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "username or password doesnt matched", null);
    }

    public User getUserById(String id) {
        return userRepository.getOne(connection, id);
    }

    public List<User> getAllUser() {
        return userRepository.getList(connection);
    }

    public String register(User user) {
        if (userRepository.getPasswordHash(connection, user.getName()).isEmpty()) {
            userRepository.insert(connection, user);
            return "New User Registered";
        } else return "Existed User";
    }
}
