package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.model.User;

import java.util.Optional;

public interface UserService {


    Iterable<User> getAll();

    Optional<User> getOne(Integer id);

    User addUser(User user);

    void deleteUser(Integer id);

    User patchUser(Integer id, UserPatchDto dto);

}
