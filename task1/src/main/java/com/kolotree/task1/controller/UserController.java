package com.kolotree.task1.controller;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDto;
import com.kolotree.task1.dto.user.UserShowSlimDto;
import com.kolotree.task1.dto.user.UserShowWithProjectsDto;
import com.kolotree.task1.mapper.UserMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<UserShowDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserShowDto> getOne(@PathVariable Integer id) {
        UserShowDto foundUser = userService.getOne(id);

        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserShowDto addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserShowDto savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser).getBody();

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {

        if (userService.deleteUser(id)) {
            return ResponseEntity.ok("User with id " + id + " was successfully deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id:" + id + ", not found");
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@PathVariable Integer id, @Valid @RequestBody UserPatchDto dto) {
        try {
            UserShowDto updated = userService.patchUser(id, dto);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/me")
    public ResponseEntity<UserShowDto> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        User user = UserMapper.userDetailsToUser(currentUser);

        UserShowDto userShow = userService.getUserByEmail(user.getEmail());

        return ResponseEntity.ok(userShow);
    }

    @GetMapping("/meWithProjects")
    public ResponseEntity<UserShowWithProjectsDto> meWithProjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        User user = UserMapper.userDetailsToUser(currentUser);

        UserShowWithProjectsDto userShow = userService.getUserByEmailWithProject(user.getEmail());

        return ResponseEntity.ok(userShow);
    }



    @GetMapping("/meSlim")
    public ResponseEntity<UserShowSlimDto> authenticatedUserSlim() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails currentUser = (UserDetails) authentication.getPrincipal();

        User user = UserMapper.userDetailsToUser(currentUser);

        UserShowSlimDto userShow = userService.getUserByEmailSlim(user.getEmail());

        return ResponseEntity.ok(userShow);
    }

}
