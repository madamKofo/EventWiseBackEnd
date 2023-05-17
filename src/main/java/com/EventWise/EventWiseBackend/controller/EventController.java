package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.DTO.UserCreateDTO;
import com.EventWise.EventWiseBackend.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<EventDto>> getAllEventsByUserId(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(eventService.getAllEventsCreatedByUserId(userId));
    }

    @GetMapping("/{userId}/events/create")
    public String showCreateEvent(@PathVariable(value = "userId") Long userId, Model model) {
        model.addAttribute("eventDto", new EventDto());
        return "create-event";
    }

    @PostMapping("/{userId}/events")
    public ResponseEntity<EventDto> createEvent(@PathVariable Long userId, @ModelAttribute EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(userId, eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

}
