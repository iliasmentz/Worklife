package com.linkedin.entities.repo;

import com.linkedin.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findAllByCreatorIdOrderByPostDateDesc(@Param("userId") Long userId);

  @Query(value = "select post_id from post", nativeQuery = true)
  List<Long> getAllPostsIds();
}
