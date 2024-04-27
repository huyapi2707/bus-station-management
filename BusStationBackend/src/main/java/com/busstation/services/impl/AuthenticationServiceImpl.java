package com.busstation.services.impl;

import com.busstation.dto.AuthenticationRequest;
import com.busstation.dto.AuthenticationResponse;
import com.busstation.dto.RegisterRequest;
import com.busstation.dto.mappers.UserDTOMapper;
import com.busstation.pojo.Role;
import com.busstation.pojo.User;
import com.busstation.repositories.RoleRepository;
import com.busstation.repositories.UserRepository;
import com.busstation.services.AuthenticationService;
import com.busstation.services.JwtService;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.function.Function;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userDetailsService;

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
                .userDetails(userDetailsService.toDTO(userDetails))
                .build();
    }


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = (User) userDetailsService.loadUserByUsername(registerRequest.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("User name  is exist");
        } else {
            if (userDetailsService.isEmailExist(registerRequest.getEmail())) {
                throw new IllegalArgumentException("Email is exist");
            }
        }
        if (user.getEmail().equals(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email is exist");
        }
        Role role = roleRepository.getRoleByName(registerRequest.getRole());
        if (role == null) {
            throw new IllegalArgumentException("Role field is invalid");
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
                .userDetails(userDetailsService.toDTO(newUser))
                .build();
    }
}
