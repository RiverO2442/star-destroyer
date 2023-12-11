package org.rivero.roommanagement.controllers;

import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    List<User> userList = new ArrayList<>();
    UserService userService = new UserService();

    @GetMapping("/user")
    public List<User> getUser() {
        try {
            return userService.getAllUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.register(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
          return ResponseEntity.ok().body(userService.login(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        try {
            if(userService.getUserById(userId) != null)
                return ResponseEntity.ok().body(userService.getUserById(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(new User(userId, "null", "null", 0));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable(name = "userId") Integer userId, @RequestBody User user) {
        userList.remove(userId.intValue());
        userList.add(userId, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") Integer userId) {
        userList.remove(userId.intValue());
        return ResponseEntity.noContent().build();
    }
}
