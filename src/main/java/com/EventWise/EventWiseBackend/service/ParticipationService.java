package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.CreateParticipationRequest;
import com.EventWise.EventWiseBackend.DTO.ParticipantDto;
import com.EventWise.EventWiseBackend.entities.Participation;

import java.util.List;

public interface ParticipationService {
    Participation createParticipation(CreateParticipationRequest createParticipationRequest);
    void approveParticipation(Long participationId);
    void cancelParticipation(Long participationId);

    List<ParticipantDto> getParticipantsForEvent(Long eventId);
  //  List<ParticipantDto> getParticipantsForEventCreatedByUser(Long eventId);
}
