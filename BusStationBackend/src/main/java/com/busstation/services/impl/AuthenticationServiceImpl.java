package com.busstation.services.impl;

import com.busstation.controllers.auth.AuthenticationRequest;
import com.busstation.controllers.auth.AuthenticationResponse;
import com.busstation.controllers.auth.RegisterRequest;
import com.busstation.pojo.Role;
import com.busstation.pojo.User;
import com.busstation.repositories.RoleRepository;
import com.busstation.repositories.UserRepository;
import com.busstation.services.AuthenticationService;
import com.busstation.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
      manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
      User userDetails = (User) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
      return AuthenticationResponse.builder()
              .accessToken(jwtService.generateToken(userDetails))
              .role((userDetails.getRole().getName()))
              .build();
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = (User) userDetailsService.loadUserByUsername(registerRequest.getUsername());
        if (user != null && user.getEmail().equals(registerRequest.getEmail())) {
            throw new IllegalArgumentException("User name or email is exist");
        }
        Role role = roleRepository.getRoleByName(registerRequest.getRole());
        if (role == null) {
            throw  new IllegalArgumentException("Role field is invalid");
        }
        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .isActive(true)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();
        userRepository.saveUser(newUser);
        return AuthenticationResponse.builder()
                .accessToken(jwtService.generateToken(newUser))
                .role(newUser.getRole().getName())
                .build();
    }
}
