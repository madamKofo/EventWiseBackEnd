package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.CreateParticipationRequest;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.EventNotFoundException;
import com.EventWise.EventWiseBackend.exceptions.UserNotFoundException;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import com.EventWise.EventWiseBackend.repository.ParticipationRepository;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {
    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public ParticipationServiceImpl(ParticipationRepository participationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Participation createParticipation(CreateParticipationRequest createParticipationRequest) {
        Participation participation = new Participation();

        User participant = userRepository.findById(createParticipationRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Event eventToParticipate = eventRepository.findById(createParticipationRequest.getEventId())
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        //Organiser should not register as participant
        if (participant.getEmail() == eventToParticipate.getEventOrganiser().getEmail()) {
            throw new UserNotFoundException("Organiser cannot be same as participant");
        }

        //user should not register for same event twice
        boolean isParticipantRegistered = participationRepository.existsByEventAndUser(eventToParticipate, participant);
        if (isParticipantRegistered) {
            throw new IllegalStateException("Participant is already registered for the event");
        }

        participation.setEvent(eventToParticipate);
        participation.setUser(participant);
        participation.setRegisteredAt(LocalDateTime.now());
        participation.setOrganiserHasApproved(true);
        participation.setParticipantHasApproved(false);
        return participationRepository.save(participation);
    }

    public void approveParticipation(Long participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid participation ID"));
        participation.setParticipantHasApproved(true);
        participationRepository.save(participation);
    }

    public void cancelParticipation(Long participationId) {
        Participation participation = participationRepository.findById(participationId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid participation ID"));
        participationRepository.delete(participation);
    }
}
