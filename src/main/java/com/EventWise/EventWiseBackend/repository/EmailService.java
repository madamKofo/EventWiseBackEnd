package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.DTO.EventParticipantDto;

public interface EmailService {
    void sendEmail(EventParticipantDto eventParticipantDto, Long eventId);
}
