package com.busstation.controllers;

import com.busstation.dtos.UserDTO;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")

public class ApiUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/self")
    public ResponseEntity<UserDTO> getSelfInformation() {
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }
}
