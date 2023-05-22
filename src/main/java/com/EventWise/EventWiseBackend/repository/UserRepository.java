package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   Optional<User> findByDisplayName(String displayName);

   Optional<User> findByDisplayNameAndPassword(String displayName, String password);
}
