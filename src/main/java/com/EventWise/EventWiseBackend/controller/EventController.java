package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.EventNotFoundException;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "entity controller", description = "description")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            throw new EventNotFoundException("Event with ID " + id + " not found");
        }
    }

    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event){
        return ResponseEntity.ok(eventService.saveEvent(event));
    }

  /*  @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event){
        return ResponseEntity.ok(eventService.(id, event));
    }*/

  /*  @DeleteMapping("/events")
    public ResponseEntity<Void> deleteAllEvents(){
        eventService.();
        return ResponseEntity.noContent().build();
    }*/

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
