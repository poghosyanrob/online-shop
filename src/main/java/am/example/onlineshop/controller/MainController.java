package am.example.onlineshop.controller;

import am.example.onlineshop.model.Role;
import am.example.onlineshop.service.security.SpringUser;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${online.shop.system.upload.image.directory.path}")
    private String imageDirectoryPath;

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(@AuthenticationPrincipal SpringUser userPrincipal){
        if (Role.USER == userPrincipal.getUser().getUserType()){
            return "redirect:/user/home";
        } else if (Role.ADMIN == userPrincipal.getUser().getUserType()) {
            return "redirect:/admin/home";
        }
        return null;
    }

    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("pic") String picName){
        File file = new File(imageDirectoryPath + picName);
        if(file.exists()){
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
