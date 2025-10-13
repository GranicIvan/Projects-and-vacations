package com.kolotree.task1.service.implementation;

import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.mapper.UserMapper;
import com.kolotree.task1.model.User;
import com.kolotree.task1.repository.UserRepository;
import com.kolotree.task1.service.interfaces.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtServiceImpl jwtService;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserShowDTO signup(RegisterUserDto input) {
        User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setUserType(input.getUserType());

        return UserMapper.toShowDto(userRepository.save(user));
    }

    @Override
    public String authenticate(LoginUserDto input) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(input.getEmail(), input.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        // Get user details from the authenticated object
        UserDetails userDetails = (UserDetails) authenticationResponse.getPrincipal();

        // Generate the JWT token
        return jwtService.generateToken(userDetails);
    }
}
