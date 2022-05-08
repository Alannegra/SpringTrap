package com.example.demo.resources;

import com.example.demo.controllers.UserController;
import com.example.demo.model.User;
import com.example.demo.repositories.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
    public List<UserDTO> users() {
        return userController.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDTO user(@PathVariable("id") int id){return userController.getUser(id);}
    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO){
        userController.addUser(userDTO);
    }
    @PutMapping("{id}")
    public void putUser(@RequestBody UserDTO userDTO, @PathVariable("id") int id){userController.putUser(userDTO, id);}
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") int id){
        userController.deleteUser(id);
    }
    @GetMapping("{id}/email")
    public Map<String, String> email(@PathVariable("id") int id){return Collections.singletonMap("email",userController.getUser(id).getEmail());}
    @PatchMapping("{id}") public void patchUser(@PathVariable("id") int id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {userController.patchUser(id,patch);}
}
