package com.busstation.controllers;

import com.busstation.repositories.TripRepository;
import com.busstation.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trip")
public class ApiTripController {
    @Autowired
    TripService service;

    @GetMapping("/{id}/seat-details")
    public ResponseEntity<Object> handleSeatDetail(@PathVariable Long id) {
        return ResponseEntity.ok(service.seatDetails(id));
    }
}
