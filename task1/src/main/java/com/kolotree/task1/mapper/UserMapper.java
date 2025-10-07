package com.kolotree.task1.mapper;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserUpdateDto;
import com.kolotree.task1.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserMapper {

    public static void applyUpdate(User target, UserUpdateDto dto) {
        target.setFirstName(dto.getFirstName());
        target.setLastName(dto.getLastName());
        target.setDateOfBirth(dto.getDateOfBirth());
        target.setEmail(dto.getEmail());
        target.setAddress(dto.getAddress());
        target.setVacationDaysLeft(dto.getVacationDaysLeft());

    }

    public static void applyPatch(User target, UserPatchDto dto) {
        if (dto.getFirstName() != null)        target.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null)         target.setLastName(dto.getLastName());
        if (dto.getDateOfBirth() != null)      target.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getEmail() != null)            target.setEmail(dto.getEmail());
        if (dto.getAddress() != null)          target.setAddress(dto.getAddress());
        if (dto.getVacationDaysLeft() != null) target.setVacationDaysLeft(dto.getVacationDaysLeft());
    }

}
