package com.jyeh.blogweb.repository;

import com.jyeh.blogweb.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post.id=:postId")
    List<Comment> findByPostId(Long postId);
}
