package com.jyeh.blogweb.service;

import com.jyeh.blogweb.domain.Post;
import com.jyeh.blogweb.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostRepository postRepository;
    private PostService postService;
    private Post post;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
        post = Post.builder().author("u1").content("a post").build();
    }

    @Test
    void addPost() {
        postService.addPost(post);
        verify(postRepository).save(post);
    }

    @Test
    void getPosts() {
        postService.getPosts();
        verify(postRepository).findAll();
    }

    @Test
    void getPostById() {
        postService.getPostById(anyLong());
        verify(postRepository).findById(anyLong());
    }

    @Test
    void deletePostById() {
        postService.deletePostById(anyLong());
        verify(postRepository).deleteById(anyLong());
    }
}