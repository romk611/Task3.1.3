package com.example.task312.dao;


import com.example.task312.model.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAllRole();

    Role getRole(String userRole);

    Role getRole(long roleId);

    void addRole(Role role);
}
