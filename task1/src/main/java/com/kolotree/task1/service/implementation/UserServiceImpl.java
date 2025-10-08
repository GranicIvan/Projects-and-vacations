package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.mapper.UserMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.UserRepo;
import com.kolotree.task1.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Override
    public Iterable<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<User> getOne(Integer id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public User addUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        if (!userRepo.existsById(id)) return ResponseEntity.notFound().build();
        userRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> patchUser(Integer id, UserPatchDto dto) {

        //Body must same at least one field
        if (dto.getFirstName() == null && dto.getLastName() == null &&
                dto.getDateOfBirth() == null && dto.getEmail() == null &&
                dto.getAddress() == null && dto.getVacationDaysLeft() == null) {
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
