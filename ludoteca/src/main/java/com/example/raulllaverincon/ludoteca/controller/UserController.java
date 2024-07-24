package com.example.raulllaverincon.ludoteca.controller;

import com.example.raulllaverincon.ludoteca.model.User;
import com.example.raulllaverincon.ludoteca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/borrow/{itemId}")
    public ResponseEntity<Void> borrowItem(@PathVariable Long userId, @PathVariable Long itemId) {
        userService.borrowItem(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/return/{itemId}")
    public ResponseEntity<Void> returnItem(@PathVariable Long userId, @PathVariable Long itemId) {
        userService.returnItem(userId, itemId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/penalize")
    public ResponseEntity<Void> penalizeUser(@PathVariable Long userId) {
        userService.penalizeUser(userId);
        return ResponseEntity.ok().build();
    }
}