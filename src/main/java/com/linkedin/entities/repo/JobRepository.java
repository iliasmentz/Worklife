package com.linkedin.entities.repo;

import com.linkedin.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
  List<Job> findAllByAuthorId(Long userId);
}
