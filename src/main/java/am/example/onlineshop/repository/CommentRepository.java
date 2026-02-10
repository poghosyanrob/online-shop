package am.example.onlineshop.repository;

import am.example.onlineshop.model.Comment;
import am.example.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findByProduct(Product product);
}
