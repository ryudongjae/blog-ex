package com.blog.blogex.n_plus_1.post;

import com.blog.blogex.n_plus_1.comment.Comment;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @AfterEach
    public void cleanAll(){
        postRepository.deleteAll();
    }

    public void setUp(){
        List<Post>posts = new ArrayList<>();

        for(int i = 0;  i< 10; i++){
            Post post = Post.builder()
                    .name("게시글"+i)
                    .build();
            post.addComment(Comment.builder()
                            .content("댓글"+i)
                            .build());
            posts.add(post);
        }
        postRepository.saveAll(posts);
    }

}