package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.DTO.UserRegistrationDto;
import com.EventWise.EventWiseBackend.entities.Login;
import com.EventWise.EventWiseBackend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/user/signup")
    public String showSignUp(Model model) {
        model.addAttribute("user", new UserCreateDTO());
        return "sign-up";
    }

    @GetMapping(value = {"/"})
    public String showLogIn(Model model) {
        var loginData = new Login();
        model.addAttribute("login", loginData);
        return "home";
    }


    @PostMapping("/user/signup")
    public String createUser(@ModelAttribute UserCreateDTO userCreateDto) {
       var user = userService.save(userCreateDto);
        return "redirect:/user/" + user.getId() + "/events/event-list";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute Login login, Model model) {
        var user = userService.FindUserByEmailAndPassword(login.getUserName(), login.getPassword());
        if (user == null) {
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
            return "login";
        }

        return "redirect:/user/" + user.getId() + "/events/event-list";
    }



/*    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserCreateDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new UserCreateDTO());
        return "user-create";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserCreateDTO userCreateDTO) {
        userService.createUser(userCreateDTO);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        UserDto user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute UserCreateDTO updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }*/
}
