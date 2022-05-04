package com.example.demo.resources;

import com.example.demo.controllers.UserController;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(UserResource.USER_RESOURCE)
public class UserResource {
    public final static String USER_RESOURCE = "/users";
    UserController userController;


    @Autowired
    public UserResource(UserController userController){
        this.userController = userController;
    }
    @GetMapping
    public List<User> users() {
        return userController.getAllUsers();
    }

    @GetMapping("{id}")
    public User user(@PathVariable("id") String id){
        return userController.getUser(id);
    }
    @PostMapping
    public void addUser(@RequestBody User user){
        userController.addUser(user);
    }
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") String id){
        userController.deleteUser(id);
    }
    @GetMapping("{id}/email")
    public Map<String, String> email(@PathVariable("id") String id){
        return Collections.singletonMap("email",userController.getUser(id).getEmail());
    }
}
