package com.linkedin.entities.repo;

import com.linkedin.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
  List<Like> findAllByPostId(Long postId);

  List<Like> findAllByUserId(Long userId);
}
