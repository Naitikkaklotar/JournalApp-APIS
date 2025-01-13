package com.naitik.journalapp.controller;

import com.naitik.journalapp.entity.RedisUser;
import com.naitik.journalapp.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @PostMapping
    public RedisUser saveUser(@RequestBody RedisUser user) {
        return userDAO.save(user);
    }

    @GetMapping("/{id}")
    public RedisUser getUser(@PathVariable String id) {
        return userDAO.findById(id);
    }

    @GetMapping
    public List<RedisUser> getAllUsers() {
        return userDAO.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userDAO.delete(id);
    }
}
