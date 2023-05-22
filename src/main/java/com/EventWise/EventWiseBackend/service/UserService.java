package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<UserCreateDTO> getAllUsers();

    UserDto createUser(UserCreateDTO userCreateDTO);
    User findUserByUserName(String userName) throws UserNotFoundException;
    UserDto findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException;
    UserDto getUserById(Long id);

    UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO);

    boolean deleteUser(Long id);

    String getUserName(Long id);
}

