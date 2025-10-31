package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.user.*;
import com.kolotree.task1.model.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@NoArgsConstructor
public class UserMapper {

    public static void applyUpdate(User target, UserUpdateDto dto) {
        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setDateOfBirth(dto.getDateOfBirth());
        target.setEmail(dto.getEmail());
        target.setAddress(dto.getAddress());
        target.setVacationDaysLeft(dto.getVacationDaysLeft());
        target.setUserRole(dto.getUserRole());
        target.setActiveStatus(dto.isActiveStatus());

    }

    public static void applyPatch(User target, UserPatchDto dto) {
        if (dto.getFirstName() != null) target.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) target.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null) target.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getEmail() != null) target.setEmail(dto.getEmail());
        if (dto.getAddress() != null) target.setAddress(dto.getAddress());
        if (dto.getVacationDaysLeft() != null) target.setVacationDaysLeft(dto.getVacationDaysLeft());


    }

    public static UserShowDto toShowDto(User user) {
        return new UserShowDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getAddress(),
                user.getVacationDaysLeft(),
                user.getUserRole(),
                user.getProjectAssignment(),
                user.getVacationRequests()
        );
    }

    public static UserShowWithProjectsDto toShowDtoWithProject(User user) {
        return new UserShowWithProjectsDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getAddress(),
                user.getVacationDaysLeft(),
                user.getUserRole(),
                ProjectAssignmentMapper.toShowDtoList(user.getProjectAssignment()),
                user.getVacationRequests()
        );
    }

    public static UserShowSlimDto toShowSlimDto(User user) {
        return new UserShowSlimDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getAddress(),
                user.getVacationDaysLeft(),
                user.getUserRole()
        );
    }

    public static List<UserShowDto> toShowDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::toShowDto)
                .toList();
    }

    public static User userDetailsToUser(UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getUsername());
        return user;
    }


}
