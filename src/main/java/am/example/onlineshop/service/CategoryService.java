package am.example.onlineshop.service;

import am.example.onlineshop.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();
    Category save(Category category);
    void deleteById(Integer id);
    Optional<Category> findById(Integer id);

}
