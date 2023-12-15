package org.rivero.roommanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.repositories.UserRepository;
import org.rivero.roommanagement.services.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public List<User> getUser() {
        return userService.getAllUser();
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.login(user));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        if (userService.getUserById(userId) != null)
            return ResponseEntity.ok().body(userService.getUserById(userId));
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).build();
    }

//    @PutMapping("/user/{userId}")
//    public ResponseEntity<Void> updateUserById(@PathVariable(name = "userId") Integer userId, @RequestBody User user) {
//        userList.remove(userId.intValue());
//        userList.add(userId, user);
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/user/{userId}")
//    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") Integer userId) {
//        userList.remove(userId.intValue());
//        return ResponseEntity.noContent().build();
//    }
}
