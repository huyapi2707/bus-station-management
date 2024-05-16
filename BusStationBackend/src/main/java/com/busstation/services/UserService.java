package com.busstation.services;

import com.busstation.dtos.UserDTO;
import com.busstation.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    boolean isEmailExist(String email);
    UserDTO toDTO(User user);

    UserDTO getAuthenticatedUser();
}
