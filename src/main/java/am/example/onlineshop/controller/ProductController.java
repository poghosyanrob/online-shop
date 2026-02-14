package am.example.onlineshop.controller;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.Comment;
import am.example.onlineshop.model.Product;
import am.example.onlineshop.service.CategoryService;
import am.example.onlineshop.service.CommentService;
import am.example.onlineshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CommentService commentService;

    @GetMapping("/admin/product")
    public String products(ModelMap modelMap) {
        List<Product> products = productService.findAll();
        modelMap.addAttribute("products", products);
        products.forEach(product -> {
            List<Comment> comments = commentService.findByProduct(product);
            product.setComments(comments);
        });
        return "admin/product/product";
    }

    @GetMapping("/admin/product/add")
    public String addProduct(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories",categories);
        return "admin/product/addProduct";
    }

    @PostMapping("/admin/product/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam(value = "pic") MultipartFile multipartFile) {
        productService.save(product,multipartFile);
        return "redirect:/admin/product";

    }

    @GetMapping("/admin/product/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        List<Comment> comments = commentService.findByProduct(productService.findById(id).orElseThrow());
        comments.forEach(
                comment -> {
                    commentService.deleteById(comment.getId());
                }
        );
        productService.deleteById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update")
    public String editProduct(@RequestParam("id") int id, ModelMap modelMap) {

        productService.findById(id).ifPresent(
                product -> {
                    modelMap.addAttribute("product",product);
                }
        );
        List<Category> categories = categoryService.findAll();
        modelMap.addAttribute("categories",categories);
        return "admin/product/updateProduct";
    }

    @PostMapping("/admin/product/update")
    public String editProduct(@ModelAttribute Product product,
                              @RequestParam(value = "pic") MultipartFile multipartFile) {
        product.setComments(product.getComments());
        productService.save(product,multipartFile);
        return "redirect:/admin/product";

    }

    @GetMapping("/user/product/own/page")
    public String showOwnProductPage(@RequestParam("id") int id,ModelMap modelMap) {
        productService.findById(id).ifPresent(
                product -> {
                    modelMap.addAttribute("product",product);
                    modelMap.addAttribute("comments",commentService.findByProduct(product));
                }
        );

        return "user/productOwnPage";
    }

}
