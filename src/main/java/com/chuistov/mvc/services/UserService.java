package com.chuistov.mvc.services;

import com.chuistov.mvc.entities.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    // TODO Add managerFactory argument from hiber config
    private EntityManager entityManager =
            Persistence.createEntityManagerFactory("").createEntityManager();

    private static long USER_COUNT;

    private final List<User> userList = new ArrayList<>(List.of(
            new User(++USER_COUNT, "Ivan", "Ivanov", 26),
            new User(++USER_COUNT, "Peter", "Petrov", 36),
            new User(++USER_COUNT, "Tom", "Soyer", 56),
            new User(++USER_COUNT, "Dow", "Jones", 63),
            new User(++USER_COUNT, "Kathy", "Smith", 6)
    ));

    private final User nullUser = new User(-1, "no name", "no last name", -1);

    public List<User> getAllUsers() {
        return userList;
    }

    public User getUserById(long id) {
        return userList.stream()
                .filter(x -> x.getId() == id)
                .findFirst()
                .orElse(nullUser);
    }

    public void saveUser(User user) {
        user.setId(++USER_COUNT);
        userList.add(user);
    }

    public void updateUser(User updatedUser, long id) {
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setAge(updatedUser.getAge());
    }

    public void deleteUserById(long id) {
        userList.removeIf(user -> user.getId() == id);
    }
}