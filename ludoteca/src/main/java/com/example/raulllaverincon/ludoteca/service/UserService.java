package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Item;
import com.example.raulllaverincon.ludoteca.model.User;
import com.example.raulllaverincon.ludoteca.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findUsersByRole(String role) {
        return userRepository.findAll().stream()
                .filter(user -> role.equals(user.getRole()))
                .collect(Collectors.toList());
    }

    public List<User> findUsersWithOverdueItems() {
        // User has a method getItemsBorrowed() which returns a list of borrowed items,
        // Item has a method isOverdue() to check if the item is overdue.
        return userRepository.findAll().stream()
                .filter(user -> user.getItemsBorrowed().stream().anyMatch(Item::isOverdue))
                .collect(Collectors.toList());
    }

    public void borrowItem(Long userId, Long itemId) {
        userRepository.findById(userId).ifPresent(user -> {
            // Logic to borrow an item, e.g., adding to the list of borrowed items
            user.getItemsBorrowed().add(findItemById(itemId));
            userRepository.save(user);
        });
    }

    public void returnItem(Long userId, Long itemId) {
        userRepository.findById(userId).ifPresent(user -> {
            // Logic to return an item, e.g., removing from the list of borrowed items
            user.getItemsBorrowed().removeIf(item -> false);
            userRepository.save(user);
        });
    }

    public void penalizeUser(Long userId) {
        userRepository.findById(userId).ifPresent(user -> {
            // Logic to penalize a user
            user.setPenalized(true);
            userRepository.save(user);
        });
    }

    private Item findItemById(Long itemId) {
        // Logic to find an item by its ID
        // This can be a call to another repository or service
        return new Item(); // Placeholder for actual implementation
    }

    @PostConstruct
    public void init() {
        String username = "testuser";
        if (userRepository.findByUsername(username).isEmpty()) {
            User testUser = new User();
            testUser.setUsername(username);
            testUser.setPassword("password");
            testUser.setRole("customer");
            testUser.setPenalized(false);
            userRepository.save(testUser);
        }
    }
}