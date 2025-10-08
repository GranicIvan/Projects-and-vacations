package com.kolotree.task1.service.interfaces;

import com.kolotree.task1.dto.user.UserPatchDto;
import com.kolotree.task1.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {


    Iterable<User> getAll();

    ResponseEntity<User> getOne(Integer id);

    User addUser(User user);

    ResponseEntity<Void> deleteUser(Integer id);

    ResponseEntity<?> patchUser(Integer id, UserPatchDto dto);

}
