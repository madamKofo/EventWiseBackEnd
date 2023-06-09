package com.EventWise.EventWiseBackend.repository;

import com.EventWise.EventWiseBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
