package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "entity controller", description = "description")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/user/{userId}/events")
    public ResponseEntity<List<EventDto>> getAllEventsByUserId(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(eventService.getAllEventsCreatedByUserId(userId));
    }

    @PostMapping("/user/{userId}/events")
    public ResponseEntity<EventDto> createEvent(@PathVariable Long userId, @RequestBody EventDto eventDto) {
        EventDto createdEvent = eventService.createEvent(userId, eventDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

}
