package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import com.EventWise.EventWiseBackend.repository.ParticipationRepository;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    public Participation createParticipation(User user, Event event) {
        Participation participation = new Participation();
        participation.setUser(user);
        participation.setEvent(event);
        participation.setOrganiserHasApproved(false);
        participation.setParticipantHasApproved(false);
        return participationRepository.save(participation);
    }

    public List<Participation> getAllParticipations() {
        return (List<Participation>) participationRepository.findAll();
    }

    public List<Participation> getParticipationsByUser(User user) {
        return participationRepository.findByUser(user);
    }

    public List<Participation> getParticipationsByEvent(Event event) {
        return participationRepository.findByEvent(event);
    }

    public void approveParticipationByOrganiser(Participation participation) {
        participation.setOrganiserHasApproved(true);
        participationRepository.save(participation);
    }

    public void approveParticipationByParticipant(Participation participation) {
        participation.setParticipantHasApproved(true);
        participationRepository.save(participation);
    }

    public void deleteParticipation(Participation participation) {
        participationRepository.delete(participation);
    }
}

