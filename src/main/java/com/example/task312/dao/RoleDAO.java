package com.example.task312.dao;


import com.example.task312.model.Role;

import java.util.Set;

public interface RoleDAO {
    Set<Role> getAllRole();

    Role getRole(String userRole);

    Role getRole(long roleId);

    void addRole(Role role);
}
