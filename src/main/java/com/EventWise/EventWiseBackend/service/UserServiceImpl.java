package com.EventWise.EventWiseBackend.service;


import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.mapper.UserMapper;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserCreateDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.toCreateDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserCreateDTO userCreateDTO) {
        User user = modelMapper.toEntity(userCreateDTO);
        userRepository.save(user);
        return modelMapper.toDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return modelMapper.toDto(user);
    }

    @Override
    public UserCreateDTO updateUser(Long id, UserCreateDTO userCreateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // update user fields
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setDisplayName(userCreateDTO.getDisplayName());

        User savedUser = userRepository.save(user);
        return modelMapper.toCreateDto(savedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        return true;
    }
}

