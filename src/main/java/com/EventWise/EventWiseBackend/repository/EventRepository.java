package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e JOIN e.eventOrganiser u WHERE u.id = :userId")
    List<Event> findAllByEventOrganiserId(@Param("userId") Long userId);
}
