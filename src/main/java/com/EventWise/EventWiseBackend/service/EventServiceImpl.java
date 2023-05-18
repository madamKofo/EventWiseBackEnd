package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.DTO.ParticipantDto;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.EventNotFoundException;
import com.EventWise.EventWiseBackend.exceptions.UnauthorizedAccessException;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.mapper.EventMapper;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public List<EventDto> getAllEventsCreatedByUserId(Long userId) {
        List<Event> events = eventRepository.findAllByEventOrganiserId(userId);
        var eventDTOs = events.stream().map(e ->eventMapper.toDTO(e)).toList();
        return eventDTOs;
    }

    @Override
    public EventDto createEvent(Long userId, EventDto eventDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setEventDate(eventDto.getEventDate());
        event.setEventDescription(eventDto.getEventDescription());
        event.setEventAddress(eventDto.getEventAddress());
        event.setPublic(eventDto.isPublic());
        event.setEventImageUrl(eventDto.getEventImageUrl());
        event.setEventOrganiser(user);

        Event savedEvent = eventRepository.save(event);

        return eventMapper.toDTO(savedEvent);
    }

    @Override
    public List<ParticipantDto> getEventParticipants(Long eventId, Long userId) {
        // Check if the event exists
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        // Check if the user is the event organizer
       /* if (!event.getEventOrganiser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("You are not authorized to access the participants of this event");
        }*/

        List<ParticipantDto> participants = new ArrayList<>();

        for (Participation participation : event.getParticipations()) {
            User participantUser = participation.getUser();
            ParticipantDto participantDto = new ParticipantDto();
            participantDto.setId(participantUser.getId());
            participantDto.setFirstName(participantUser.getFirstName());
            participantDto.setLastName(participantUser.getLastName());
            participantDto.setEmail(participantUser.getEmail());
            participantDto.setOrganiserHasApproved(participation.isOrganiserHasApproved());
            participantDto.setParticipantHasApproved(participation.isParticipantHasApproved());

            participants.add(participantDto);
        }

        return participants;
    }


}
