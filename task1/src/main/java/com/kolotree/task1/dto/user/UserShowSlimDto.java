package com.kolotree.task1.dto.user;

import com.kolotree.task1.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class UserShowSlimDto {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    private String address;
    private Integer vacationDaysLeft;

    private UserRole userRole;
}
