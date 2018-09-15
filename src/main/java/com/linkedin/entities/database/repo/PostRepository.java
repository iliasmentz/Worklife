package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Education;
import com.linkedin.entities.database.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  @Query(value = "select e from Post e where e.creatorId = :userId")
  List<Post> findByUserId(@Param("userId")Long userId);
}
