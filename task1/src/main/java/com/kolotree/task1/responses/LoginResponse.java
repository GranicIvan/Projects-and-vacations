package com.kolotree.task1.responses;


import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class LoginResponse {
    private String token;

    private long expiresIn;
}
