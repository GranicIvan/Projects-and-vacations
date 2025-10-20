package com.kolotree.task1.controller;

import com.kolotree.task1.dto.auth.LoginResponseDto;
import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.service.interfaces.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @Value("${cookie.duration}")
    private Integer cookieDuration;

    @PostMapping("/signup")
    public ResponseEntity<UserShowDTO> register(@RequestBody RegisterUserDto registerUserDto) {
        UserShowDTO registerUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto, HttpServletResponse response) {
        String jwtToken = authenticationService.authenticate(loginUserDto);

        LoginResponseDto loginResponseDto = new LoginResponseDto();

        response.addHeader(HttpHeaders.SET_COOKIE, createJwtCookie(jwtToken, cookieDuration).toString());

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        response.addHeader(HttpHeaders.SET_COOKIE, createJwtCookie("", 0).toString());
        return ResponseEntity.ok().build();
    }

    private ResponseCookie createJwtCookie(String token, long maxAgeSeconds) {
        return ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(maxAgeSeconds)
                .sameSite("Strict")
                .build();
    }

    @GetMapping("/testNoJwt")
    public String testNoJwt() {
        Date date = new Date();
        return "Test No JWT Works correctly at: " + date;
    }

    @GetMapping("/testNoJwtJson")
    public ResponseEntity testNoJwtJson() {
        Date date = new Date();
        return ResponseEntity.ok("{Test No JWT Works correctly at: " + date + "}");
    }


}
