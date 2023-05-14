package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
