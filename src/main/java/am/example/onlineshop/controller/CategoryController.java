package am.example.onlineshop.controller;

import am.example.onlineshop.model.Category;
import am.example.onlineshop.model.User;
import am.example.onlineshop.service.CategoryService;
import am.example.onlineshop.service.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public String categories(ModelMap modelMap,
                             @AuthenticationPrincipal SpringUser userPrincipal) {
        List<Category> categories = categoryService.findAll();
        User user = userPrincipal.getUser();
        modelMap.addAttribute("categories", categories);
        modelMap.addAttribute("user",user);
        return "category/category";
    }

    @GetMapping("/category/add")
    public String addCategory() {
        return "category/addCategory";
    }

    @PostMapping("/category/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/category";

    }

    @GetMapping("/category/delete")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteById(id);
        return "redirect:/category";
    }

    @GetMapping("/category/update")
    public String editCategory(@RequestParam("id") int id, ModelMap modelMap) {

        categoryService.findById(id).ifPresent(
                category -> {
                    modelMap.addAttribute("category",category);
                }
        );
        return "category/updateCategory";
    }

    @PostMapping("/category/update")
    public String editCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/category";

    }


}
