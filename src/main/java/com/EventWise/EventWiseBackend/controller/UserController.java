package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserDto;
import com.EventWise.EventWiseBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            model.addAttribute("userCreateDTO", userDto);
            return "user-details"; // Return the name of the Thymeleaf template
        } else {
            throw new NotFoundException("User not found"); // Customize exception handling as per your requirements
        }
    }


    @GetMapping("/create")
    public String showCreateUser(Model model) {
        model.addAttribute("userCreateDTO", new UserCreateDTO());
        return "create-user";
    }

    @PostMapping
    public String createUser(@ModelAttribute("userCreateDTO") UserCreateDTO userCreateDTO) {
        UserDto userDto = userService.createUser(userCreateDTO);
        return "redirect:/users/" + userDto.getId(); // Redirect to the user details page
    }


    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("userCreateDTO") UserCreateDTO userCreateDTO) {
        UserCreateDTO updatedUserCreateDTO = userService.updateUser(id, userCreateDTO);
        if (updatedUserCreateDTO != null) {
            return "redirect:/users/" + id; // Redirect to the updated user details page
        } else {
            throw new NotFoundException("User not found"); // Customize exception handling as per your requirements
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return "redirect:/users"; // Redirect to the user listing page
        } else {
            throw new NotFoundException("User not found"); // Customize exception handling as per your requirements
        }
    }


}

