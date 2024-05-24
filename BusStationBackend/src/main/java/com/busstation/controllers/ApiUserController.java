package com.busstation.controllers;

import com.busstation.dtos.UserDTO;
import com.busstation.pojo.User;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @GetMapping("/self")
    public ResponseEntity<UserDTO> getSelfInformation() {
        return ResponseEntity.ok(userService.getAuthenticatedUser());
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> listActiveUsers() {
        List<UserDTO> users = userService.listActiveUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<List<UserDTO>> findActiveUsersByRoleId(@PathVariable Long id) {
        List<UserDTO> users = userService.findActiveUsersByRoleId(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

