package com.blog.blogex.n_plus_1.comment;

import com.blog.blogex.n_plus_1.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id@GeneratedValue
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content, Post post) {
        this.content = content;
        this.post = post;
    }

    public void updatePost(Post post){
        this.post = post;
    }
}
