package com.jyeh.blogweb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String content;
    private LocalDateTime timestamp;
    @JsonIgnore
    @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, mappedBy = "post")
    private List<Comment> comments;

    public Post addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
        comment.setPost(this);
        return this;
    }
}
