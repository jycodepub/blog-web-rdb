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
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;

    private Post post;

    @BeforeEach
    void setUp() {
        post = Post.builder().author("user1")
                .content("A simple post")
                .timestamp(LocalDateTime.now())
                .build();

    }

    @Test
    public void basic_crud_test() {
        postRepository.save(post);

        Comment comment = Comment.builder().author("user2").content("Single post").build();
        post.addComment(comment);

        commentRepository.save(comment);
        assertThat(comment.getId()).isNotNull();

        commentRepository.deleteById(comment.getId());
        assertThat(commentRepository.findById(comment.getId()).isEmpty());
    }

    @Test
    public void get_comments_by_post_id() {
        post.addComment(Comment.builder().author("a1").content("c1").build())
                .addComment(Comment.builder().author("a2").content("c2").build())
                .addComment(Comment.builder().author("a3").content("c3").build());
        postRepository.save(post);

        List<Comment> comments = commentRepository.findByPostId(post.getId());
        assertThat(comments.size()).isEqualTo(3);
        assertThat(comments.get(0).getAuthor()).isEqualTo("a1");
        assertThat(comments.get(1).getAuthor()).isEqualTo("a2");
        assertThat(comments.get(2).getAuthor()).isEqualTo("a3");
    }
}