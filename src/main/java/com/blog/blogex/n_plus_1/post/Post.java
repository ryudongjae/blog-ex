package com.blog.blogex.n_plus_1.post;

import com.blog.blogex.n_plus_1.comment.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id@GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(String name, List<Comment> comments) {
        this.name = name;
        if(comments != null){
            this.comments = comments;
        }
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.updatePost(this);
    }
}
