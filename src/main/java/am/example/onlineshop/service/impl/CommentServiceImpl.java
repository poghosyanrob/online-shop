package am.example.onlineshop.service.impl;

import am.example.onlineshop.model.Comment;
import am.example.onlineshop.model.Product;
import am.example.onlineshop.repository.CommentRepository;
import am.example.onlineshop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;


    @Override
    public List<Comment> findByProduct(Product product) {
        return commentRepository.findByProduct(product);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }
}
