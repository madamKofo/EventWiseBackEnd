package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.UserDTO;
import com.EventWise.EventWiseBackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO createUser(UserDTO userDTO);

    Optional<User> getUserById(Long id);
    UserDTO updateUser(Long id, UserDTO userDTO);
    boolean deleteUser(Long id);
}

