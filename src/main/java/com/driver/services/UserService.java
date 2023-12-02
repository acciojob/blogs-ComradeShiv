package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    public User createUser(String username, String password){
        User user = new User(username, password);

        // save new user
        User savedUser = userRepository3.save(user);

        return savedUser;
    }

    public void deleteUser(int userId){
        Optional<User> optionalUser = userRepository3.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        // delete user
        userRepository3.deleteById(user.getId());
    }

    public User updateUser(Integer id, String password){
        Optional<User> optionalUser = userRepository3.findById(id);
        if(!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        // updating password
        User user = optionalUser.get();
        user.setPassword(password);

        // save changes
        User savedUser = userRepository3.save(user);

        return savedUser;
    }
}
