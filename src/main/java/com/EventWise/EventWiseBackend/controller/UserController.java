package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;
import com.EventWise.EventWiseBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userCreateDTO = userService.getUserById(id);
        if (userCreateDTO != null) {
            return ResponseEntity.ok(userCreateDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateDTO userCreateDTO) {
        UserDto userDto = userService.createUser(userCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCreateDTO> updateUser(@PathVariable Long id, @RequestBody UserCreateDTO userCreateDTO) {
        UserCreateDTO updatedUserCreateDTO = userService.updateUser(id, userCreateDTO);
        if (updatedUserCreateDTO != null) {
            return ResponseEntity.ok(updatedUserCreateDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

