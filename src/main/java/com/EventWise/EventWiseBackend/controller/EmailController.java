package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.EventParticipantDto;
import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.repository.EmailService;
import com.EventWise.EventWiseBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    public EmailController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @PostMapping(value = "/user/{userId}/events/{eventId}/send-email", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String sendEmail(@PathVariable(value = "userId") Long userId,
                            @PathVariable(value = "eventId") Long eventId,
                            @Valid @ModelAttribute("eventParticipantDto") EventParticipantDto eventParticipantDto,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return "create-participant";
        }

        // Send the email
        emailService.sendEmail(eventParticipantDto, eventId);
        model.addAttribute("userId", userId);
        model.addAttribute("username", userService.getUserName(userId));

        // Redirect to the desired page after sending the email
        return "redirect:/user/{userId}/events/participants";
    }

    @GetMapping("/user/{userId}/events/{eventId}/send-email")
    public String showCreateParticipant(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, Model model)  {
        model.addAttribute("userId", userId);
        model.addAttribute("eventId", eventId);
        model.addAttribute("eventParticipantDto", new EventParticipantDto());
        model.addAttribute("username", userService.getUserName(userId));
        return "create-participant";
    }

}
