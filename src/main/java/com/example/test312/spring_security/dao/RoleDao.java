package com.example.test312.spring_security.dao;

import com.example.test312.spring_security.model.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getAllRole();

    public Role getRole(String userRole);

    public Role getRole(long roleId);

    public void addRole(Role role);
}
