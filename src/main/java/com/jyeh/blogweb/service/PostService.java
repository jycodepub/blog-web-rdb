package com.jyeh.blogweb.service;

import com.jyeh.blogweb.domain.Post;
import com.jyeh.blogweb.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
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
}
