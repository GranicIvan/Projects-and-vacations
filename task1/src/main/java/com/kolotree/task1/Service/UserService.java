package com.kolotree.task1.Service;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.mapper.UserMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.UserRepo;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;

    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userRepo.existsById(id)) return ResponseEntity.notFound().build();
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> patchUser(@PathVariable Integer id, @Valid @RequestBody UserPatchDto dto) {

        //Body must same at least one field
        if (dto.getFirstName() == null && dto.getLastName() == null &&
                dto.getDateOfBirth() == null && dto.getEmail() == null &&
                dto.getAddress() == null && dto.getVacationDaysLeft() == null &&
                dto.getUserType() == null) {
            return ResponseEntity.badRequest().body("At least one field must be provided.");
        }

        //Name can't be blank
        if (dto.getFirstName() != null && dto.getFirstName().isBlank())
            return ResponseEntity.badRequest().body("firstName must not be blank when provided.");
        if (dto.getLastName() != null && dto.getLastName().isBlank())
            return ResponseEntity.badRequest().body("lastName must not be blank when provided.");


        return userRepo.findById(id)
                .map(existing -> {
                    UserMapper.applyPatch(existing, dto);
                    var saved = userRepo.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
