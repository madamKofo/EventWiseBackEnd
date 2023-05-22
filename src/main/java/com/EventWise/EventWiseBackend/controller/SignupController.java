/*
package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.service.AuthorityService;
import com.EventWise.EventWiseBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;


    @GetMapping
    public String showSignupPage(@ModelAttribute("user") UserCreateDTO user, @ModelAttribute("message") String message,
                                 @ModelAttribute("messageType") String messageType, Model model) {
        model.addAttribute("authorities", authorityService.findAllAuthorities());
        return "auth/signup";
    }

    @PostMapping
    public String postSignup(UserCreateDTO user, RedirectAttributes redirectAttributes) {
        try {
            userService.findUserByUserName(user.getDisplayName());
            redirectAttributes.addFlashAttribute("message", "User already exists!");
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/signup";
        } catch(UserNotFoundException userNotFoundException) {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "Signup successful!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/";
        }
    }
}
*/
