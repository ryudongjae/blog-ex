package com.blog.blogex.n_plus_1.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    /**
     * 1. join fetch를 통한 조회
     */
    @Query("select a from Post a join fetch a.comments")
    List<Post>findAllFetchJoin();

    /**
     * 2. @EntityGraph
     */
    @EntityGraph(attributePaths = "comments")
    @Query("select a from Post a")
    List<Post> findAllEntityGraph();

    /**
     * 3. join fetch + distinct 를 통한 조회
     */
    @Query("select DISTINCT a from Post a join fetch a.comments")
    List<Post> findAllJoinFetchDistinct();

    /**
     * 4. @EntityGraph + distinct 를 통한 조회
     */

    @EntityGraph(attributePaths = "comments")
    @Query("select DISTINCT a from Post a")
    List<Post> findAllEntityGraphDistinct();

}
