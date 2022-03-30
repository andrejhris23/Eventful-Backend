package app.eventful.service;

import app.eventful.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Long id);

    Comment editComment(Comment newComment);

    Comment createComment(String content, Long postId, Long userId);

    void deleteById(Long id);
}
