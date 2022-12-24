package com.chuistov.mvc.services;

import com.chuistov.mvc.dao.UserDao;
import com.chuistov.mvc.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User add(User user) {
        return userDao.add(user);
    }

    public void delete(long id) {
        userDao.delete(id);
    }

    public User get(long id) {
        return userDao.get(id);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public List<User> getAll() {
        return userDao.getAll();
    }
}