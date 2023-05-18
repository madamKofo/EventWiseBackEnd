package com.EventWise.EventWiseBackend.DTO;

import com.EventWise.EventWiseBackend.entities.Event;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {
    private Long eventId;
    private LocalDate eventDate;
    private String eventName;
    private String eventDescription;
    private String eventAddress;
    private String eventImageUrl;
    private boolean isPublic;
    private String eventOrganiser;

    public EventDto() {}

    public EventDto(Event event) {
        this.eventId = event.getId();
        this.eventDate = event.getEventDate();
        this.eventName = event.getEventName();
        this.eventDescription = event.getEventDescription();
        this.eventAddress = event.getEventAddress();
        this.eventImageUrl = event.getEventImageUrl();
        this.isPublic = event.isPublic();
        this.eventOrganiser = event.getEventOrganiser().getDisplayName();
    }
}
