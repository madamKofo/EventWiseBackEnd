package com.EventWise.EventWiseBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private String eventImageUrl;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User eventOrganiser;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private Set<Participation> participations = new HashSet<>();
}