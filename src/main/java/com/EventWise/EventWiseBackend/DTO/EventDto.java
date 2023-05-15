package com.EventWise.EventWiseBackend.DTO;

import com.EventWise.EventWiseBackend.entities.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {
    private LocalDate eventDate;
    private String eventName;
    private String eventDescription;
    private String eventAddress;
    private boolean isPublic;
    private String eventOrganiser;

    public EventDto() {}

    public EventDto(Event event) {
        this.eventDate = event.getEventDate();
        this.eventName = event.getEventName();
        this.eventDescription = event.getEventDescription();
        this.eventAddress = event.getEventAddress();
        this.isPublic = event.isPublic();
        this.eventOrganiser = event.getEventOrganiser().getDisplayName();
    }
}
