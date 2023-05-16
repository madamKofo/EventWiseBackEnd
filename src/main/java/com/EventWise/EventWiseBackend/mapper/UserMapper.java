package com.EventWise.EventWiseBackend.mapper;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;
import com.EventWise.EventWiseBackend.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserCreateDTO toCreateDto(User user) {
        return modelMapper.map(user, UserCreateDTO.class);
    }

    public UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User toEntity(UserCreateDTO userCreateDTO) {
        return modelMapper.map(userCreateDTO, User.class);
    }
}
