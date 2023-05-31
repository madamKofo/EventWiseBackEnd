package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserRegistrationDto;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setDisplayName(userCreateDTO.getDisplayName());
        user.setPassword(userCreateDTO.getPassword());

        return userRepository.save(user);
    }

    @Override
    public User FindUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null)
            return null;

        if (user.getPassword().equals(password))
            return user;

        return null;
    }

    @Override
    public String getUserName(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user.getDisplayName();
    }

 /*   private final UserRepository userRepository;
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
    public UserDto findUserByUserNameAndPassword(String userName, String password) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findByDisplayNameAndPassword(userName, password);

        return optionalUser.map(modelMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userName, password));
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return modelMapper.toDto(user);
    }

    @Override
    public String getUserName(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user.getDisplayName();
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
    }*/
}
