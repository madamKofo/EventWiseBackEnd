package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;

import com.EventWise.EventWiseBackend.entities.User;

public interface UserService {
   /* List<UserCreateDTO> getAllUsers();

    UserDto createUser(UserCreateDTO userCreateDTO);
    User findUserByDisplayName(String displayName) throws UserNotFoundException;
    UserDto findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException;
    UserDto getUserById(Long id);

    UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO);

    boolean deleteUser(Long id);

    String getUserName(Long id);*/

   String getUserName(Long id);
   User save(UserCreateDTO userCreateDTO);
   User FindUserByEmailAndPassword(String email, String password);

}

