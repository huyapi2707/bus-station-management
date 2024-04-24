package com.busstation.controllers.auth;

import com.busstation.dto.AuthenticationRequest;
import com.busstation.dto.AuthenticationResponse;
import com.busstation.dto.RegisterRequest;
import com.busstation.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
}
