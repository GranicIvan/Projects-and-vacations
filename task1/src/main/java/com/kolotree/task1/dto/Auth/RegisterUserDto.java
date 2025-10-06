package com.kolotree.task1.dto.Auth;

import lombok.Data;

@Data
public class RegisterUserDto {
    private String email;

    private String password;

    private String firstName;
    private String lastName;
}
