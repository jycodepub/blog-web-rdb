package com.jyeh.blogweb.controller;

import com.jyeh.blogweb.domain.Comment;
import com.jyeh.blogweb.domain.Post;
import com.jyeh.blogweb.service.CommentService;
import com.jyeh.blogweb.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    private final PostService postService;
    private final CommentService commentService;

    public BlogController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // Post Routes
    @PostMapping("/posts")
    public ResponseEntity<Response> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Response.builder().status(Response.Status.success)
                        .message("Added post: id=" + post.getId())
                        .build()
        );
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }

    // Comment
    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Response> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return postService.getPostById(id).map(p -> {
                comment.setPost(p);
                commentService.addComment(comment);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                    Response.builder().status(Response.Status.success)
                            .message("Added comment: id=" + comment.getId())
                            .build()
                );
        })
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        Response.builder().status(Response.Status.failed)
                                .error("Post not found. id= " + id)
                                .build()
                ));
    }

    @GetMapping("/posts/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return commentService.getCommentsByPost(id);
    }
}
