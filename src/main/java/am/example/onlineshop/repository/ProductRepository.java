package am.example.onlineshop.repository;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByCategory(Category category);
}
