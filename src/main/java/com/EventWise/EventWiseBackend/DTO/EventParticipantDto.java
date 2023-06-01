package com.EventWise.EventWiseBackend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventParticipantDto {
    private String firstName;
    private String lastName;
    private String email;
    private String emailSubject;
    private String emailBody;
}
