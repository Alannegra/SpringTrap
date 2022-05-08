package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repositories.UserDAO;
import com.example.demo.repositories.UserDTO;
import com.example.demo.repositories.UserRepository;

import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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

    public List<UserDTO> getAllUsers() {
        List<User> users = userDao.findAll();
        List<UserDTO> usersDto = new ArrayList<>();
        for (User u : users) {
            usersDto.add(new UserDTO(u));
        }
        return usersDto;
    }

    public void addUser(UserDTO userDto) {User user = new User(userDto);userDao.save(user);}

    public void putUser(UserDTO userDto, int id) {
        User user = new User(userDto);

        User real = new User(getUser(id));

        real.setId(user.getId());
        real.setEmail(user.getEmail());
        real.setPassword(user.getPassword());
        real.setFullName(user.getFullName());

        userDao.save(real);
    }

    public UserDTO getUser(int id) {
        Optional<User> users = userDao.findById(id);
        UserDTO userDto = new UserDTO(users.get());
        return userDto;
    }

    public void deleteUser(int id){
        userDao.deleteById(id);
    }

    public void patchUser(int id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        User user = new User(getUser(id));
        User userPatched = applyPatch(patch, user);
        userDao.save(userPatched);

    }

    private User applyPatch(JsonPatch patch, User targetUser) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode patched = patch.apply(objectMapper.convertValue(targetUser, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }
}
