package com.example.task312.dao;

import com.example.task312.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<User> getAllUser() {
        List<User> userList = entityManager.createQuery("select u from User u", User.class).getResultList();
        return new HashSet<>(userList);
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserByUsername(String email) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.email = :login", User.class);
            return query.setParameter("login", email)
                    .getSingleResult();
        } catch (Exception e) {
            return User.NOBODY;
        }
    }
}
