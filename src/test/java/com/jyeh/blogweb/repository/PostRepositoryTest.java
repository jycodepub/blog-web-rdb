package com.jyeh.blogweb.repository;

import com.jyeh.blogweb.domain.Comment;
import com.jyeh.blogweb.domain.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    private Post post;

    @BeforeEach
    public void init() {
        post = Post.builder().author("user1")
                .content("A simple post")
                .timestamp(LocalDateTime.now())
                .build();
        post.addComment(Comment.builder().author("user2")
                .content("Nice post")
                .timestamp(LocalDateTime.now())
                .build());
    }

    @AfterEach
    public void tearDown() {
        post = null;
    }

    @Test
    public void basic_crud_test() {
        postRepository.save(post);
        assertThat(post.getId()).isNotNull();

        List<Post> posts = postRepository.findByAuthor("user1");
        assertThat(posts.size()).isEqualTo(1);
        assertThat(post.getComments().size()).isEqualTo(posts.get(0).getComments().size());
        assertThat(posts.get(0).getAuthor()).isEqualTo("user1");
        assertThat(posts.get(0).getComments().get(0).getAuthor()).isEqualTo("user2");

        postRepository.deleteById(post.getId());
        posts = postRepository.findByAuthor("user1");
        assertThat(posts.size()).isEqualTo(0);
    }
}