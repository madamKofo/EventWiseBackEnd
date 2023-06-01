package com.EventWise.EventWiseBackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String displayName;
    @OneToMany(mappedBy = "eventOrganiser")
    private Set<Event> event = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Participation> participations = new HashSet<>();



}

