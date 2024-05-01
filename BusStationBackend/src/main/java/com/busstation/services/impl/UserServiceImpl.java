package com.busstation.services.impl;

import com.busstation.dtos.UserDTO;
import com.busstation.dtos.mappers.UserDTOMapper;
import com.busstation.pojo.User;
import com.busstation.repositories.UserRepository;
import com.busstation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOMapper userDTOMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.getUserByUserName(s);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.isEmailExist(email);
    }

    @Override
    public UserDTO toDTO(User user) {
        return userDTOMapper.apply(user);
    }
}
