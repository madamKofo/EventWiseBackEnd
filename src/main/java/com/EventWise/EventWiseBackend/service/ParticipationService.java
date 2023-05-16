package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.CreateParticipationRequest;
import com.EventWise.EventWiseBackend.entities.Participation;

public interface ParticipationService {
    Participation createParticipation(CreateParticipationRequest createParticipationRequest);
    void approveParticipation(Long participationId);
    void cancelParticipation(Long participationId);
}
