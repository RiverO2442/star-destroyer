package org.rivero.roommanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.rivero.roommanagement.entities.User;
import org.rivero.roommanagement.request.LoginRequest;
import org.rivero.roommanagement.request.UserUpdateRequest;
import org.rivero.roommanagement.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUser() {
        return userService.getAllUser();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.register(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        if (userService.getUserById(userId) != null)
            return ResponseEntity.ok(userService.getUserById(userId));
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUserById(@PathVariable(name = "userId") String userId, @RequestBody UserUpdateRequest user) {
        userService.updateOne(user, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable(name = "userId") String userId) {
        userService.deleteOne(userId);
        return ResponseEntity.noContent().build();
    }
}
