package org.rivero.roommanagement.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    List<User> userList = new ArrayList<>();

    @GetMapping("/user")
    public List<User> getUser() {
        return userList;
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userList.add(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Integer userId) {
        return ResponseEntity.ok().body(userList.get(userId));
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
