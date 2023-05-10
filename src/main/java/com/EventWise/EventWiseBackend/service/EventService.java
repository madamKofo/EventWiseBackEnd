package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.entities.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Event createEvent(Event event);

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long eventId);

    Event updateEvent(Long id, Event event);

    String deleteEventById(Long eventId);

    String deleteAllEvent();
}
