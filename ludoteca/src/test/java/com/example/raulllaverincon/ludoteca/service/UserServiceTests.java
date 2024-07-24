package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.User;
import com.example.raulllaverincon.ludoteca.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindAllUsers() {
        List<User> users = userService.findAllUsers();
        assertThat(users).isNotEmpty();
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("password");
        user.setRole("USER");
        user = userRepository.save(user);

        User foundUser = userService.findUserById(user.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("TestUser");
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveUser() {
        User user = new User();
        user.setUsername("NewUser");
        user.setPassword("password");
        user.setRole("USER");

        User savedUser = userService.saveUser(user);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("ToBeDeleted");
        user.setPassword("password");
        user.setRole("USER");
        user = userRepository.save(user);

        userService.deleteUser(user.getId());
        User deletedUser = userService.findUserById(user.getId()).orElse(null);
        assertThat(deletedUser).isNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testPenalizeUser() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("password");
        user.setRole("USER");
        user.setPenalized(false);
        user = userRepository.save(user);

        userService.penalizeUser(user.getId());

        User penalizedUser = userService.findUserById(user.getId()).orElse(null);
        assertThat(penalizedUser).isNotNull();
        assertThat(penalizedUser.isPenalized()).isTrue();
    }
}