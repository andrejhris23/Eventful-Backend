package app.eventful.service.impl;

import app.eventful.model.Post;
import app.eventful.model.User;
import app.eventful.model.exceptions.InvalidPostIdException;
import app.eventful.model.exceptions.InvalidUserIdException;
import app.eventful.repository.PostRepo;
import app.eventful.repository.UserRepo;
import app.eventful.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    public PostServiceImpl(PostRepo postRepo, UserRepo userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new InvalidPostIdException(id));
    }

    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Post createPost(String title, String content, Long userId) {
        User postAuthor = userRepo.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        Post newPost = new Post(title, content, postAuthor);

        return postRepo.save(newPost);
    }

    @Override
    public Post editPost(Long postId, String title, String content) {
        Post editedPost = postRepo.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));
        editedPost.setTitle(title);
        editedPost.setContent(content);

        return postRepo.save(editedPost);
    }

    @Override
    public Post likePost(Long postId, Long userId) {
        User currentUser = userRepo.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        Post likedPost = postRepo.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        if(likedPost.getLikesFromUsers().contains(currentUser)) {
            throw new InvalidPostIdException(postId);
        } else {
            likedPost.getLikesFromUsers().add(currentUser);
            return postRepo.save(likedPost);
        }
    }

    @Override
    public int calculateLikes(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));

        return post.getLikesFromUsers().size();
    }
}
