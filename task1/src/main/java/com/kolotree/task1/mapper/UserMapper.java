package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.dto.user.UserUpdateDto;
import com.kolotree.task1.model.User;
import lombok.NoArgsConstructor;
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
        target.setUserType(dto.getUserType());
//        target.setActiveStatus(dto.isActiveStatus());

    }

    public static void applyPatch(User target, UserPatchDto dto) {
        if (dto.getFirstName() != null)        target.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)         target.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null)      target.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getEmail() != null)            target.setEmail(dto.getEmail());
        if (dto.getAddress() != null)          target.setAddress(dto.getAddress());
        if (dto.getVacationDaysLeft() != null) target.setVacationDaysLeft(dto.getVacationDaysLeft());


    }

    public static UserShowDTO toShowDto(User user) {
        return new UserShowDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getEmail(),
                user.getAddress(),
                user.getVacationDaysLeft(),
                user.getUserType(),
                user.getProjectAssignment(),
                user.getVacations()
        );


    }

    public static List<UserShowDTO> toShowDtoList(List<User> users) {
        return users.stream()
            .map(UserMapper::toShowDto)
            .toList();
        }
}
