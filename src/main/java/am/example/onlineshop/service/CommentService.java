package am.example.onlineshop.service;

import am.example.onlineshop.model.Comment;
import am.example.onlineshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findByProduct(Product product);
    void save(Comment comment);
    void deleteById(Integer id);
    Optional<Comment> findById(Integer id);

}
