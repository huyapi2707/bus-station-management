package com.busstation.controllers;

import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/route")
public class ApiRouteController {

    @Autowired
    private RouteService service;

    @GetMapping("/list")
    public ResponseEntity<Object> list(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(service.list(params));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<Object> retrieve(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/{id}/trip")
    public ResponseEntity<Object> handleRouteDetails(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTripList(id));
    }
}
