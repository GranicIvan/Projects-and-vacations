package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.mapper.UserMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.UserRepository;
import com.kolotree.task1.service.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getOne(Integer id) {
        return userRepository.findById(id);

    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User patchUser(Integer id, UserPatchDto dto) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Validate that at least one field is present
        if (dto.getFirstName() == null && dto.getLastName() == null &&
                dto.getDateOfBirth() == null && dto.getEmail() == null &&
                dto.getAddress() == null && dto.getVacationDaysLeft() == null) {
            throw new IllegalArgumentException("At least one field must be provided.");
        }

        // Validate non-blank names
        if (dto.getFirstName() != null && dto.getFirstName().isBlank()) {
            throw new IllegalArgumentException("firstName must not be blank when provided.");
        }
        if (dto.getLastName() != null && dto.getLastName().isBlank()) {
            throw new IllegalArgumentException("lastName must not be blank when provided.");
        }

        UserMapper.applyPatch(user, dto);
        return userRepository.save(user);

    }
}
