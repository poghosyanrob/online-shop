package am.example.onlineshop.service;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();
    void save (Product product);
    Product save (Product product, MultipartFile multipartFile);
    void deleteById(Integer id);
    Optional<Product> findById(Integer id);
    List<Product> findByCategory(Category category);

}
