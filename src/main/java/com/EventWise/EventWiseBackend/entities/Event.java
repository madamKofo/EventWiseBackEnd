package com.EventWise.EventWiseBackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate eventDate;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = true)
    private String eventDescription;
    @Column(nullable = true)
    private String eventAddress;
    private boolean isPublic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User eventOrganiser;
    @OneToMany(mappedBy = "event")
    private Set<Participation> participations = new HashSet<>();
}