package am.example.onlineshop.service.impl;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.Product;
import am.example.onlineshop.repository.ProductRepository;
import am.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Value("${online.shop.system.upload.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product save(Product product, MultipartFile multipartFile) {

        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);

            try {
                multipartFile.transferTo(file);
                product.setPicName(fileName);
            } catch (IOException e) {
                log.error("Error while saving image for product {} : {}", product.getTitle(), e.getMessage());
            }
        } else {
            productRepository.findById(product.getId())
                    .ifPresent(productOptional -> product.setPicName(productOptional.getPicName()));
        }

        return productRepository.save(product);
    }


    @Override
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}
