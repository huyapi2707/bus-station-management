package com.busstation.services;

import com.busstation.dto.UserDTO;
import com.busstation.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    boolean isEmailExist(String email);
    UserDTO toDTO(User user);
}
