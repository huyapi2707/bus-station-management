package com.busstation.services;

import com.busstation.dto.AuthenticationRequest;
import com.busstation.dto.AuthenticationResponse;
import com.busstation.dto.RegisterRequest;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);

}
