package com.kolotree.task1.controller;

import com.kolotree.task1.dto.auth.LoginResponseDto;
import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.model.User;
import com.kolotree.task1.service.implementation.JwtServiceImpl;
import com.kolotree.task1.service.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtServiceImpl jwtServiceImpl;

    private final AuthenticationService authenticationServiceImpl;


    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registerUser = authenticationServiceImpl.signup(registerUserDto);

        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationServiceImpl.authenticate(loginUserDto);

        String jwtToken = jwtServiceImpl.generateToken(authenticatedUser);

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtServiceImpl.getExpirationTime());

        return ResponseEntity.ok(loginResponseDto);
    }
}
