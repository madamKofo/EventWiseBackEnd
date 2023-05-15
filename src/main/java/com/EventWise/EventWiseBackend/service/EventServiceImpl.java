package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.mapper.EventMapper;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        event.setEventOrganiser(user);

        Event savedEvent = eventRepository.save(event);

        return eventMapper.toDTO(savedEvent);
    }

}
