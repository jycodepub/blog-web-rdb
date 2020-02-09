package com.jyeh.blogweb.controller;

import com.jyeh.blogweb.domain.Comment;
import com.jyeh.blogweb.domain.Post;
import com.jyeh.blogweb.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Post Routes
    @PostMapping("/posts")
    public ResponseEntity<?> addPost(@RequestBody Post post) {
        blogService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added blog: id=" + post.getId());
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return blogService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return blogService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        blogService.deletePostById(id);
    }

    // Comment
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        blogService.addComment(id, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Added comment: id=" + comment.getId());
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return blogService.getCommentsByPost(id);
    }
}
