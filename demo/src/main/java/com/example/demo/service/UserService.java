package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserDAO;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserDAO userDAO;

    public List<User> findAll(){
        return userDAO.findAll();
    }

    public User saveUser(User user) {
        return UserDAO.save(user);
    }
    public void deleteUser(UUID uuid) {
        UserDAO.deleteById(uuid);
    }
    public User updateUser(User user) {
        return UserDAO.save(user);
    }
    public boolean existById(UUID uuid) {
        return UserDAO.existsById(uuid);
    }


}
