package com.chuistov.mvc.dao;

import com.chuistov.mvc.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User add(User user) {
        return sessionFactory.getCurrentSession().merge(user);
    }

    public void delete(long id) {
        sessionFactory.getCurrentSession().remove(get(id));
    }

    public User get(long id) {
        return sessionFactory.getCurrentSession().find(User.class, id);
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    public List<User> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User")
                .getResultList();
    }
}