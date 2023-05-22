package com.EventWise.EventWiseBackend.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean organiserHasApproved;
    private boolean participantHasApproved;

    public ParticipantDto() {}

    public ParticipantDto(Long id, String firstName, String lastName, String email, boolean organiserHasApproved, boolean participantHasApproved) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.organiserHasApproved = organiserHasApproved;
        this.participantHasApproved = participantHasApproved;
    }
}
