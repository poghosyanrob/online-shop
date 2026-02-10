package am.example.onlineshop.service;

import am.example.onlineshop.model.User;

import java.util.Optional;

public interface UserService {

    void save (User user);

    Optional<User> findByEmail(String username);

}
