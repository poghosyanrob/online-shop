package am.example.onlineshop.service;

import am.example.onlineshop.model.User;

import java.util.List;
import java.util.Optional;

public interface MembersService {

    List<User> findAll();
    void save (User member);
    void deleteById(Integer id);
    Optional<User> findById(Integer id);
}
