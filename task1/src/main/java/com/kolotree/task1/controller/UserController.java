package com.kolotree.task1.controller;

import com.kolotree.task1.service.implementation.UserServiceImpl;
import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;


    @GetMapping
    public Iterable<User> getAll() {
        return userServiceImpl.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        return userServiceImpl.getOne(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userServiceImpl.addUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        return userServiceImpl.deleteUser(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Integer id, @Valid @RequestBody UserPatchDto dto) {
        return userServiceImpl.patchUser(id, dto);
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

}
