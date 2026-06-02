package services;

import entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
