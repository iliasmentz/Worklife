package com.linkedin.database.repo;

import com.linkedin.database.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
