package com.chuistov.mvc.services;

import com.chuistov.mvc.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static long USER_COUNT;

    private final List<User> userList = List.of(
            new User(++USER_COUNT, "Ivan", "Ivanov", 26),
            new User(++USER_COUNT, "Peter", "Petrov", 36),
            new User(++USER_COUNT, "Tom", "Soyer", 56),
            new User(++USER_COUNT, "Dow", "Jones", 63),
            new User(++USER_COUNT, "Kathy", "Smith", 6)
    );

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

    public void save(User user) {
        user.setId(++USER_COUNT);
        userList.add(user);
    }
}
