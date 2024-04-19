package com.busstation.controllers.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest implements Serializable {
    private String username;
    private String email;
    private String password;
    private String role;
}
