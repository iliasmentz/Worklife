package com.linkedin.entities.repo;

import com.linkedin.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findAllByPostId(Long postId);

  List<Comment> findAllByCommenterId(Long userId);
}
