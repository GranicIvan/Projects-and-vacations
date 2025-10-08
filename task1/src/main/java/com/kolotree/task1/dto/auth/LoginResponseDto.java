package com.kolotree.task1.dto.auth;


import lombok.Data;


@Data
public class LoginResponseDto {
    private String token;

    private long expiresIn;
}
