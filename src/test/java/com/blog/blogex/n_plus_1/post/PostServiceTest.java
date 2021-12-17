package com.blog.blogex.n_plus_1.post;

import com.blog.blogex.n_plus_1.comment.Comment;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @BeforeEach
    public void deleteAll(){
        postRepository.deleteAll();
    }

    @BeforeEach
    public void setUp(){
        List<Post>posts = new ArrayList<>();

        for(int i = 0;  i< 10; i++){
            Post post = Post.builder()
                    .name("게시글"+i)
                    .build();
            post.addComment(Comment.builder()
                            .content("댓글"+i)
                            .build());
            post.addComment(Comment.builder()
                    .content("댓글1"+i)
                    .build());
            posts.add(post);
        }
        postRepository.saveAll(posts);
    }


    @Test
    @DisplayName("게시글 조회시 댓글 조회 N+1 발생")
    void N_plus_1_O()throws Exception{
        List<String> comments = postService.findAllComments();
        assertThat(comments.size()).isEqualTo(10);

    }

    @Test
    @DisplayName("fetchjoin으로 게시글 가져옴")
    void fetchJoin_getPost()throws Exception{
        List<Post> post = postRepository.findAllFetchJoin();
       // List<String> comments = postService.findAllSubjectNamesByJoinFetch();

        assertThat(post.size()).isEqualTo(10);
        //assertThat(comments.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("게시글 여러개를 EntityGraph 로 가져온다.")
    void entityGraph()throws Exception{

        List<Post> post = postRepository.findAllEntityGraph();
        assertThat(post.size()).isEqualTo(10);

    }
    
    @Test
    @DisplayName("distinct_FetchJoin")
    void distinct()throws Exception{
        //given
        List<Post> posts = postRepository.findAllJoinFetchDistinct();

        //then
        assertThat(posts.size()).isEqualTo(10);
        System.out.println("post size : " + posts.size());
    }
    @Test
    @DisplayName("distinct_EntityGraph")
    void distinct_EntityGraph()throws Exception{
        //given
        List<Post> posts = postRepository.findAllEntityGraphDistinct();

        //then
        assertThat(posts.size()).isEqualTo(10);
        System.out.println("post size : " + posts.size());
    }
}