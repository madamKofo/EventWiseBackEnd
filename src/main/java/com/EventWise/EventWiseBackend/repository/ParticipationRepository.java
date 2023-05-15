package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
