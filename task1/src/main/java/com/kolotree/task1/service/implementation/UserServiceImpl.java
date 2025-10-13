package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDTO;
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
    public Iterable<UserShowDTO> getAll() {

        return UserMapper.toShowDtoList(userRepository.findAll());
    }

    @Override
    public UserShowDTO getOne(Integer id) {
        return UserMapper.toShowDto(userRepository.findById(id).get());

    }

    @Override
    public UserShowDTO addUser(User user) {
        User savedUser = userRepository.save(user);
        return UserMapper.toShowDto(savedUser);
    }

    @Override
    public boolean deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }

        User user = optionalUser.get();

        if (user.getProjectAssignment().isEmpty()) {
            userRepository.deleteById(id);
        } else {
            userRepository.updateActiveStatus(id, false);
        }

        if (userRepository.existsById(id)) {
            return false;
        }
        return true;
    }

    @Override
    public UserShowDTO patchUser(Integer id, UserPatchDto dto) {

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
        return UserMapper.toShowDto(userRepository.save(user));

    }
}
