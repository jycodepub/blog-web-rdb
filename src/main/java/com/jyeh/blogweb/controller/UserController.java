package com.jyeh.blogweb.controller;

import com.jyeh.blogweb.domain.User;
import com.jyeh.blogweb.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<Response> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder().status(Response.Status.success)
                        .message("User added. id=" + user.getUsername())
                        .build()
        );
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{username}")
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }
}
