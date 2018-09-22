package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	List<JobApplication> findAllByUserId(Long userId);
}
