package com.busstation.services;

import com.busstation.dtos.UserDTO;
import com.busstation.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    boolean isEmailExist(String email);
    UserDTO toDTO(User user);
    UserDTO getAuthenticatedUser();
    List<UserDTO> listActiveUsers();
    List<UserDTO> findActiveUsersByRoleId(Long roleId);
    Optional<User> getUserById(Long id);
    void changeRole(Long userId, Long roleId);
}
