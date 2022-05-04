package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    public void addUser(User user){userRepository.add(user);}

    public User getUser(String id){
        return userRepository.findById(Integer.parseInt(id));
    }
    public void deleteUser(String id){
        userRepository.deleteById(Integer.parseInt(id));
    }
}
