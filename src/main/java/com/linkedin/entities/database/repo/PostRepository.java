package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Post;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PostRepository extends JpaRepository<Post,Long> {
}
