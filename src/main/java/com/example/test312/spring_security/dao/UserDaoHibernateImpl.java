package com.example.test312.spring_security.dao;

import com.example.test312.spring_security.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Transactional
    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserByUserName(String username) {
        try {
            System.out.println("TEST: " + username);
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u join fetch u.rolesList WHERE u.username = :login", User.class);
            User user = query.setParameter("login", username)
                    .getSingleResult();
            System.out.println("Answer: " + user.getName());
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}
