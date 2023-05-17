package com.EventWise.EventWiseBackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String showHomePage(@ModelAttribute("message") String message, @ModelAttribute("messageType") String messageType){
        return "home";
    }
}
