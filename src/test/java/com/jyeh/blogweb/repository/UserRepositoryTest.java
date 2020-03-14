package com.jyeh.blogweb.repository;

import com.jyeh.blogweb.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void basic_crud_test() {
        User user = new User("user1", "welcome1");
        userRepository.save(user);

        Optional<User> optFound = userRepository.findById("user1");
        assertThat(optFound).isPresent();
        User found = optFound.get();
        assertThat(found.getUsername()).isEqualTo(user.getUsername());

        found.setPassword("welcome2");
        userRepository.save(found);
        found = userRepository.findById("user1").get();
        assertThat(found.getPassword()).isEqualTo("welcome2");

        userRepository.delete(found);
        optFound = userRepository.findById("user1");
        assertThat(optFound).isEmpty();
    }
}