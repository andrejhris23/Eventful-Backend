package app.eventful.web.controller;

import app.eventful.model.Post;
import app.eventful.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/findAll")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Post findById(@PathVariable("id") Long id) {
        return postService.findById(id);
    }

    @PostMapping("/create")
    public Post createPost(@Valid @RequestParam String title, @RequestParam String content, @RequestParam Long userId) {
        return postService.createPost(title, content, userId);
    }

    @PatchMapping("/edit")
    public Post editPost(@Valid @RequestParam Long postId, @RequestParam String title, @RequestParam String content) {
        return postService.editPost(postId, title, content);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deletePost(@PathVariable ("id") Long id) {
        postService.deleteById(id);
    }

    @PostMapping("/like")
    public Post likePost(@RequestParam Long postId, @RequestParam Long userId) {
        return postService.likePost(postId, userId);
    }

    @GetMapping("{postId}/likes")
    public int calculateLikes(@PathVariable Long postId) {
        return postService.calculateLikes(postId);
    }
}
