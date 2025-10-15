package com.kolotree.task1.dto.auth;

import com.kolotree.task1.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;
    private String lastName;

    @NotBlank
    private UserRole userRole;
}
