package com.linkedin.entities.repo;

import com.linkedin.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
  List<JobApplication> findAllByUserId(Long userId);

  List<JobApplication> findAllByJobId(Long jobPdId);
}

