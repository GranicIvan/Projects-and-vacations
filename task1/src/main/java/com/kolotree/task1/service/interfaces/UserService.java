package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDTO;
import com.kolotree.task1.model.User;

public interface UserService {


    Iterable<UserShowDTO> getAll();

    UserShowDTO getOne(Integer id);

    UserShowDTO addUser(User user);

    boolean deleteUser(Integer id);

    UserShowDTO patchUser(Integer id, UserPatchDto dto);

}
