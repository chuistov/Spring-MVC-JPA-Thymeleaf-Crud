package com.chuistov.mvc.dao;

import com.chuistov.mvc.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    private final EntityManager entityManager =
            Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public User add(User user) {
        entityManager.getTransaction().begin();
        User userFromDb = entityManager.merge(user);
        entityManager.getTransaction().commit();
        return userFromDb;
    }

    public void delete(long id) {
        entityManager.getTransaction().begin();
        entityManager.remove(get(id));
        entityManager.getTransaction().commit();
    }

    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    public void update(User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    public List<User> getAll() {
        TypedQuery<User> namedQuery = entityManager.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
}
