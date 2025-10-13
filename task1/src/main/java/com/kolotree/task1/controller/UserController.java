package com.kolotree.task1.controller;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.model.User;
import com.kolotree.task1.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<Iterable<UserShowDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserShowDTO> getOne(@PathVariable Integer id) {
        UserShowDTO foundUser = userService.getOne(id);

        if(foundUser == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);

    }

    @PostMapping
    public UserShowDTO addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserShowDTO savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser).getBody();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            if(userService.deleteUser(id)){
                return ResponseEntity.ok("User with id " + id + " was successfully deleted");
            }
            else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id:" +id+ ", not found");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Integer id, @Valid @RequestBody UserPatchDto dto) {
        try {
            UserShowDTO updated = userService.patchUser(id, dto);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

}
