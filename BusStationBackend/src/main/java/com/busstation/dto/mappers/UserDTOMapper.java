package com.busstation.dto.mappers;

import com.busstation.dto.UserDTO;
import com.busstation.pojo.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service("userDTOMapper")
public class UserDTOMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .firstname(user.getFirstName())
                .lastname(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .role(user.getRole().getName())
                .build();
    }
}
