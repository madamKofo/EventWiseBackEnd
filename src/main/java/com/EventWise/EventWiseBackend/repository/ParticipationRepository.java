package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
    boolean existsByEventAndUser(Event eventToParticipate, User participant);
}
