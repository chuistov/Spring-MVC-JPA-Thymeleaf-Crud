package com.chuistov.mvc.dao;

import com.chuistov.mvc.entities.User;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    private final LocalContainerEntityManagerFactoryBean entityManagerFactory;

    public UserDao(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public User add(User user) {
        return entityManagerFactory.getObject().createEntityManager()
                .merge(user);
    }

    public void delete(long id) {
        entityManagerFactory.getObject().createEntityManager()
                .remove(get(id));
    }

    public User get(long id) {
        return entityManagerFactory.getObject().createEntityManager()
                .find(User.class, id);
    }

    public void update(User user) {
        entityManagerFactory.getObject().createEntityManager()
                .merge(user);
    }

    public List<User> getAll() {
        return entityManagerFactory.getObject().createEntityManager()
                .createQuery("from User")
                .getResultList();
    }
}