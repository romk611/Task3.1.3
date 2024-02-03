package com.example.task312.dao;


import com.example.task312.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUser();
    public User getUserById(long id);
    public void saveUser(User user);
    public void editUser(User user);
    public void deleteUser(long id);
    public User getUserByUsername(String username);
}
