package com.chuistov.mvc.dao;

import com.chuistov.mvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public User add(User user) {
        return entityManager.merge(user);
    }

    @Transactional
    public void delete(long id) {
        entityManager.remove(get(id));
    }

    public User get(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    public List<User> getAll() {
        return entityManager.createQuery("from User").getResultList();
    }
}