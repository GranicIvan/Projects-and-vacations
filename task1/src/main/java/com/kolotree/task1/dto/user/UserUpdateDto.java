package com.kolotree.task1.dto.user;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateDto {
    @NotBlank
    private String firstName;
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

}
