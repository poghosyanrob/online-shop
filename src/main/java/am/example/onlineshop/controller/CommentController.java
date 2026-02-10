package am.example.onlineshop.controller;

import am.example.onlineshop.model.Comment;
import am.example.onlineshop.service.CommentService;
import am.example.onlineshop.service.ProductService;
import am.example.onlineshop.service.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ProductService productService;

    @GetMapping("/user/comment/delete")
    public String deleteComment(@RequestParam("id") int id,
                                @RequestParam("productId") int productId) {
        commentService.deleteById(id);
        return "redirect:/user/product/own/page?id=" + productId;
    }

    @PostMapping("/user/comment/add")
    public String addComment(
                            @RequestParam("comment") String commentText,
                             @RequestParam("productId") int productId,
                             @AuthenticationPrincipal SpringUser userPrincipal) {
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setUser(userPrincipal.getUser());
        comment.setProduct(productService.findById(productId).orElseThrow());
        commentService.save(comment);
        return "redirect:/user/product/own/page?id=" + productId;

    }



}
