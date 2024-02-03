package com.example.task312.service;

import com.example.task312.model.Role;

import java.util.*;

public interface RoleService {
    Role getRole(String userRole);

    List<Role> getAllRole();

    Role getRole(long roleId);

    void addRole(Role role);
}
