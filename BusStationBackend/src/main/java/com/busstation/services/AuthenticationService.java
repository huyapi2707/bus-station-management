package com.busstation.services;

import com.busstation.controllers.auth.AuthenticationRequest;
import com.busstation.controllers.auth.AuthenticationResponse;
import com.busstation.controllers.auth.RegisterRequest;


public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);

}
