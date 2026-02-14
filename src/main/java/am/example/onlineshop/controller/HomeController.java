package am.example.onlineshop.controller;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.Product;
import am.example.onlineshop.service.CategoryService;
import am.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping("/admin/home")
    public String adminHomePage() {
        return "admin/adminHome";
    }


    @GetMapping("/user/home")
    public String userHomePage(@RequestParam(required = false) Integer categoryId,
                               ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.findAll());
        List<Product> products;

        if (categoryId != null) {
            Category category = categoryService.findById(categoryId).orElse(null);

            if (category != null) {
                modelMap.addAttribute("selectedCategoryId", categoryId);
                products = productService.findByCategory(category);
            } else {
                products = productService.findAll();
            }

        } else {
            products = productService.findAll();
        }

        modelMap.addAttribute("products", products);
        return "user/userHome";
    }


}
