package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;

import java.util.List;

public interface UserService {
    List<UserCreateDTO> getAllUsers();
    UserDto createUser(UserCreateDTO userCreateDTO);

    UserDto getUserById(Long id);
    UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO);
    boolean deleteUser(Long id);
}

