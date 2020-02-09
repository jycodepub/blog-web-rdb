package com.jyeh.blogweb.repository;

import com.jyeh.blogweb.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
