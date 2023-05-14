package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Event saveEvent(Event event);

    void deleteEvent(Long eventId);

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long eventId);

    List<Event> getPublicEvents();

    List<Event> getEventsByOrganiser(String organiserName);

    List<User> getParticipantsByEvent(Long eventId);

    List<Event> getEventsByParticipant(Long userId);

    boolean isUserParticipant(Long eventId, Long userId);

    Participation addParticipant(Long eventId, Long userId);

    void removeParticipant(Long eventId, Long userId);

    boolean isParticipantApproved(Long eventId, Long userId);

    void approveParticipant(Long eventId, Long userId);

    void approveAllParticipants(Long eventId);
}
