package com.busstation.repositories;

import com.busstation.pojo.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


public interface UserRepository {
    User getUserByUserName(String username);
    void saveUser(User newUser);
}
