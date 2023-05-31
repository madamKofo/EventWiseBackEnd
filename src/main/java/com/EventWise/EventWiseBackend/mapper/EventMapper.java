package com.EventWise.EventWiseBackend.mapper;

import com.EventWise.EventWiseBackend.DTO.EventDto;
import com.EventWise.EventWiseBackend.entities.Event;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventMapper {
    private final ModelMapper modelMapper;

    public EventMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EventDto toDTO(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    public Event toEntity(EventDto eventDTO) {
        return modelMapper.map(eventDTO, Event.class);
    }

}
