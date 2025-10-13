package com.kolotree.task1.controller;

import com.kolotree.task1.dto.auth.LoginResponseDto;
import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.service.interfaces.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    //    @Value("${cookie.duration}") private long cookieDuration;
    private final long cookieDuration = 3600000;

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
}
