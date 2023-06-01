package com.EventWise.EventWiseBackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")  //Participant's ID
    private User user;

    private LocalDateTime registeredAt;
    private boolean organiserHasApproved;
    private boolean participantHasApproved;

}
