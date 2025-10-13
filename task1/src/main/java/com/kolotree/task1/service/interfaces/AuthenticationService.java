package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.auth.LoginUserDto;
import com.kolotree.task1.dto.auth.RegisterUserDto;
import com.kolotree.task1.model.User;

public interface AuthenticationService {

    User signup(RegisterUserDto input);

    String authenticate(LoginUserDto input);
}
