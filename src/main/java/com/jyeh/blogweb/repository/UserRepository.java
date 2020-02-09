package com.jyeh.blogweb.repository;

import com.jyeh.blogweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
