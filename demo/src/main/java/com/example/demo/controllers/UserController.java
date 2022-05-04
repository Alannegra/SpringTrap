package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repositories.UserDAO;
import com.example.demo.repositories.UserRepository;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    UserRepository userRepository;
    //private final UserService userService;
    UserDAO userDao;

    @Autowired
    public UserController(UserDAO userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        return  userDao.findAll();
    }

    public void addUser(User user){userDao.save(user);}

    public User getUser(int id){
        Optional<User> user = userDao.findById(id);
        return user.get();
    }
    public void deleteUser(int id){
        userDao.deleteById(id);
    }
}
