package com.kolotree.task1.dto.employee;

import com.kolotree.task1.model.UserType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeUpdateDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Past
    @NotNull
    private LocalDate dateOfBirth;
    @Email
    @NotBlank
    private String email;
    private String address;
    @Min(0)
    private Integer vacationDaysLeft;
    @NotNull
    private UserType userType;
}
