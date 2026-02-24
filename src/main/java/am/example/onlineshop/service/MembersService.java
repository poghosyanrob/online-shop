package am.example.onlineshop.service;

import am.example.onlineshop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MembersService {

    Page<User> findAll(Pageable pageable);
    void save (User member);
    void deleteById(Integer id);
    Optional<User> findById(Integer id);
}
