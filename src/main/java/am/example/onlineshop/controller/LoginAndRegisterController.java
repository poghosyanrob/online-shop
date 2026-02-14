package am.example.onlineshop.controller;

import am.example.onlineshop.model.User;
import am.example.onlineshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginAndRegisterController {

    private final UserService userService;


    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "login-register/loginPage";
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "login-register/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,@RequestParam String email) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return "redirect:/register?msg=Email already exists!";
        }
        userService.save(user);
        return "redirect:/loginPage?msg=Registration successful, pls login!";
    }

}
