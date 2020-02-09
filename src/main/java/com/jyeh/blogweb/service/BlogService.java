package com.jyeh.blogweb.service;

import com.jyeh.blogweb.domain.Comment;
import com.jyeh.blogweb.domain.Post;
import com.jyeh.blogweb.repository.CommentRepository;
import com.jyeh.blogweb.repository.PostRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public BlogService(PostRepository postRepository,
                       CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    // Post ops
    public void addPost(Post post) {
        if (post.getTimestamp() == null) {
            post.setTimestamp(LocalDateTime.now());
        }
        postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    // Comment ops
    public boolean addComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
                    comment.setPost(post);
                    if (comment.getTimestamp() == null) {
                        comment.setTimestamp(LocalDateTime.now());
                    }
                    commentRepository.save(comment);
                    return true;
                })
                .orElse(Boolean.FALSE);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
