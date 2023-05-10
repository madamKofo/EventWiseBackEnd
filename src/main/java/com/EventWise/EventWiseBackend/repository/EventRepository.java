package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
}
