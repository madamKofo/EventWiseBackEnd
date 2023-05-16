package com.EventWise.EventWiseBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParticipationRequest {
    private Long eventId;
    private Long userId;
}
