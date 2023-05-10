package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.EventNotFoundException;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@RestController
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
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event){
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/events")
    public ResponseEntity<Void> deleteAllEvents(){
        eventService.deleteAllEvent();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id){
        eventService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }
}
