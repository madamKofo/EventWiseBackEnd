package com.EventWise.EventWiseBackend.controller;

import com.EventWise.EventWiseBackend.DTO.CreateParticipationRequest;
import com.EventWise.EventWiseBackend.DTO.ParticipantDto;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.service.ParticipationService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participations")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }


    @PostMapping("/")
    public ResponseEntity<Participation> createParticipation(@RequestBody CreateParticipationRequest request) {
        Participation participation = participationService.createParticipation(request);
        return ResponseEntity.ok(participation);
    }

    @PutMapping("/{participationId}/approve")
    public ResponseEntity<Void> approveParticipation(@PathVariable Long participationId) {
        participationService.approveParticipation(participationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{participationId}")
    public ResponseEntity<Void> cancelParticipation(@PathVariable Long participationId) {
        participationService.cancelParticipation(participationId);
        return ResponseEntity.ok().build();
    }
}
