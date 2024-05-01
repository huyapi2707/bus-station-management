package com.busstation.controllers;

import com.busstation.dtos.AuthenticationRequest;
import com.busstation.dtos.RegisterRequest;
import com.busstation.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")

public class ApiAuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
        }
        catch (AuthenticationException exception) {
            return new ResponseEntity<>("Wrong username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            return ResponseEntity.status(201).body(authenticationService.register(registerRequest));
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
