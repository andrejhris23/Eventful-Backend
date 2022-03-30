package app.eventful.service;

import app.eventful.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(Long id);

    void deleteById(Long id);

    Post createPost(String title, String content, Long userId);

    Post editPost(Long postId, String title, String content);

    Post likePost(Long postId, Long userId);

    int calculateLikes(Long postId);
}
