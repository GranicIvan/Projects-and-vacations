package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.dto.user.UserShowDto;
import com.kolotree.task1.dto.user.UserShowSlimDto;
import com.kolotree.task1.model.User;

public interface UserService {


    Iterable<UserShowDto> getAll();

    UserShowDto getOne(Integer id);

    UserShowDto addUser(User user);

    boolean deleteUser(Integer id);

    UserShowDto patchUser(Integer id, UserPatchDto dto);

    UserShowDto getUserByEmail(String email);

    UserShowSlimDto getUserByEmailSlim(String email);

    User getCurrentUser();

    void useVacation(Long id, int useAmount);
}
