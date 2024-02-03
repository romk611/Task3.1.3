package com.example.task312.service;

import com.example.task312.model.User;

import java.util.Set;

public interface UserService {
    Set<User> getAllUser();

    User getUserById(long id);

    void saveUser(User user);

    void editUser(User user);

    void deleteUser(long id);

    User getUserByUsername(String email);

}
