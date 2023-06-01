package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.DTO.ParticipantDto;
import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.service.EventService;
import com.EventWise.EventWiseBackend.service.ParticipationService;
import com.EventWise.EventWiseBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final ParticipationService participationService;


    @Autowired
    public EventController(EventService eventService, UserService userService, ParticipationService participationService) {
        this.eventService = eventService;
        this.userService = userService;
        this.participationService = participationService;
    }

    @GetMapping("/user/{userId}/events/create")
    public String showCreateEvent(@PathVariable(value = "userId") Long userId, Model model) {
        model.addAttribute("eventDto", new EventDto());
        model.addAttribute("userId", userId);
        model.addAttribute("username", userService.getUserName(userId));
        return "create-event";
    }

    @GetMapping("/user/{userId}/events/participants")
    public String showParticipantsForUserId(@PathVariable(value = "userId") Long userId, Model model) {
        List<ParticipantDto> participants = eventService.getParticipantsForEventCreatedByUser(userId);
        model.addAttribute("participants", participants);
        model.addAttribute("userId", userId);
        model.addAttribute("username", userService.getUserName(userId));
        return "participants";
    }
    @GetMapping("/user/{userId}/events/event-list")
    public String getAllEventsByUserId(@PathVariable("userId") Long userId, Model model) {
        List<EventDto> events = eventService.getAllEventsCreatedByUserId(userId);
        model.addAttribute("events", events);
        model.addAttribute("userId", userId);
        model.addAttribute("username", userService.getUserName(userId));
        return "event-list"; // Return the name of the Thymeleaf template to display the event list
    }

    @PostMapping("/user/{userId}/events")
    public String createEvent(@PathVariable Long userId, @ModelAttribute EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(userId, eventDto);
        return "redirect:/user/" + userId + "/events/event-list";
    }

    @GetMapping("/user/{userId}/events/{eventId}/participants")
    public String showEventParticipants(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, Model model) {
        List<ParticipantDto> participants = eventService.getEventParticipants(eventId, userId);
        model.addAttribute("participants", participants);
        model.addAttribute("userId", userId);
        model.addAttribute("eventId", eventId);
        model.addAttribute("username", userService.getUserName(userId));
        return "view-participant"; // Return the name of the Thymeleaf template to display the event participants
    }

    @PostMapping("/user/{userId}/events/{eventId}")
    public String deleteEvent(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, Model model) {
       var message =  eventService.deleteEvent(eventId, userId);
        model.addAttribute("userId", userId);
        model.addAttribute("eventId", eventId);
        model.addAttribute("username", userService.getUserName(userId));
        return "redirect:/user/" + userId + "/events/event-list";
    }
}
