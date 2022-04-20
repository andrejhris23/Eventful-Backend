package app.eventful.service.impl;

import app.eventful.model.Comment;
import app.eventful.model.Post;
import app.eventful.model.User;
import app.eventful.model.exceptions.InvalidCommentIdException;
import app.eventful.repository.CommentRepo;
import app.eventful.service.CommentService;
import app.eventful.service.PostService;
import app.eventful.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    PostService postService;
    UserService userService;

    public CommentServiceImpl(CommentRepo commentRepo, PostService postService, UserService userService) {
        this.commentRepo = commentRepo;
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepo.findById(id).orElseThrow(() -> new InvalidCommentIdException(id));
    }

    @Override
    public Comment createComment(String content, Long postId, Long userId) {
        Post post = postService.findById(postId);
        User currentUser = userService.findById(userId);
        Comment newComment = new Comment(content, currentUser, post);

        return commentRepo.save(newComment);
    }

    @Override
    public Comment editComment(Comment newComment) {
        Comment editedComment = findById(newComment.getId());
        editedComment.setContent(newComment.getContent());
        editedComment.setDate(LocalDate.now());

        return commentRepo.save(editedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepo.deleteById(id);
    }
}
