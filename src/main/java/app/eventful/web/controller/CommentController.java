package app.eventful.web.controller;


import app.eventful.model.Comment;
import app.eventful.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/findAll")
    public List<Comment> findAll() { return commentService.findAll(); }

    @GetMapping("/findById/{id}")
    public Comment findById(@PathVariable("id") Long id) {
        return commentService.findById(id);
    }

    @PostMapping("/create")
    public Comment createComment(@Valid @RequestParam String content, @RequestParam Long postId, @RequestParam Long userId) {
        return commentService.createComment(content, postId, userId);
    }

    @PatchMapping("/edit")
    public Comment editComment(@Valid @RequestBody Comment editedComment) {
        return commentService.editComment(editedComment);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }
}
