package com.example.task312.dao;


import com.example.task312.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDAOHibernateImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getAllRole() {
        List<Role> roleList = entityManager.createQuery("select r from Role r", Role.class).getResultList();
        return new HashSet<>(roleList);
    }

    @Override
    public Role getRole(String userRole) {
        try {
            return entityManager.createQuery("select r from Role r where r.name =: userRole", Role.class)
                    .setParameter("userRole", userRole).getSingleResult();
        } catch (Exception e) {
            return Role.NOBODY;
        }
    }

    @Override
    public Role getRole(long roleId) {
        return entityManager.find(Role.class, roleId);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
