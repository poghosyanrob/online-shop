package am.example.onlineshop.controller;

import am.example.onlineshop.model.User;
import am.example.onlineshop.service.MembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class MembersController {

    private final MembersService membersService;

    @GetMapping("/admin/members")
    public String members(ModelMap modelMap,
                          @RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size
                          ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Sort sort = Sort.by(Sort.Direction.DESC,"id");

        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize,sort);

        Page<User> members = membersService.findAll(pageRequest);

        int totalPages = members.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().toList();
            modelMap.addAttribute("pageNumbers",pageNumbers);
        }


        modelMap.addAttribute("members",members);
        return "admin/member/members";
    }

    @GetMapping("/admin/members/delete")
    public String deleteMember(@RequestParam("id") int id) {
        membersService.deleteById(id);
        return "redirect:/admin/members";
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
