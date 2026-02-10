package am.example.onlineshop.controller;

import am.example.onlineshop.model.User;
import am.example.onlineshop.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MembersController {

    private final MembersService membersService;

    @GetMapping("/admin/members")
    public String members(ModelMap modelMap) {
        List<User> members = membersService.findAll();
        modelMap.addAttribute("members",members);
        return "admin/member/members";
    }

    @GetMapping("/admin/members/delete")
    public String deleteMember(@RequestParam("id") int id) {
        membersService.deleteById(id);
        return "redirect:/admin/member/members";
    }

    @GetMapping("/admin/members/update")
    public String editMember(@RequestParam("id") int id, ModelMap modelMap) {

        membersService.findById(id).ifPresent(
                member -> {
                    modelMap.addAttribute("member",member);
                }
        );
        return "admin/member/updateMember";
    }

    @PostMapping("/admin/members/update")
    public String editMember(@ModelAttribute User member) {
        membersService.save(member);
        return "redirect:/admin/members";

    }

}
