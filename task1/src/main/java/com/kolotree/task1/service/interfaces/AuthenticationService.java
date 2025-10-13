package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.dto.user.UserShowDTO;

public interface AuthenticationService {

    UserShowDTO signup(RegisterUserDto input);

    String authenticate(LoginUserDto input);
}
