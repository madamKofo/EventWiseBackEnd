package com.EventWise.EventWiseBackend.service;


import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;

import java.util.List;

public interface EventService {

    List<EventDto> getAllEventsCreatedByUserId(Long userId) throws UserNotFoundException;

    EventDto createEvent(Long userId, EventDto eventDto);
}
