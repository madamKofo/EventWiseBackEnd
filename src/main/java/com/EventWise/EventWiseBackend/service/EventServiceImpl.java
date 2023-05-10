package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.EventNotFoundException;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event createEvent(Event event) {
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<Event> getAllEvents() {
       var events =  eventRepository.findAll();
        return (List<Event>) events;
    }

    @Override
    public Optional<Event> getEventById(Long eventId) {
        var event =  eventRepository.findById(eventId);
        return event;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        Optional<Event> eventToBeUpdated = eventRepository.findById(id);
        if (eventToBeUpdated.isPresent()) {
            Event updatedEvent = eventRepository.save(event); //old event needs to be changed
            return updatedEvent;
        } else {
            throw new EventNotFoundException("Event with ID " + event.getId() + " not found");
        }
    }


    @Override
    public String deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
        return "Deleted Successfully";
    }

    @Override
    public String deleteAllEvent() {
        eventRepository.deleteAll();
        return "All event deleted Successfully";
    }
}
