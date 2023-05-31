package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.repository.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("user/{userId}/send-email")
    public String sendEmail(@RequestParam("email") String email,
                            @RequestParam("subject") String subject,
                            @RequestParam("body") String body,
                            @PathVariable(value = "userId") Long userId,
                            Model model) {
        // Send the email
        emailService.sendEmail(email, subject, body);
        model.addAttribute("userId", userId);
        // Redirect to the desired page after sending the email
        return "redirect:/user/{userId}/events/participants";
    }

}
