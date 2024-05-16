package com.busstation.controllers;

import com.busstation.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
