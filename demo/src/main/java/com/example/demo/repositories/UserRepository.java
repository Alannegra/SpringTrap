package com.example.demo.repositories;

import com.example.demo.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    List<User> llistaUsers = new ArrayList<>();

    public UserRepository() {
        llistaUsers.add(new User(1,"1","1","1"));
        llistaUsers.add(new User(2,"1","1","Alan"));
    }
    public List<User> findAll() {

        return llistaUsers;
    }


    /*public User xd (){
        return findById(1);
        //Query("$")
    }*/

    public void add(User user){
        llistaUsers.add(user);
    }
    public User findById(Integer id){
        Optional<User> user = llistaUsers.stream().filter(u->u.getId()==id).findAny();
        if(user.isPresent()) return user.get();
        else return null;
    }

    public void deleteById(Integer id){
        User user = findById(id);
        llistaUsers.remove(user);
    }

}
