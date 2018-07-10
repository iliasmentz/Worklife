package com.linkedin.database.repo;

import com.linkedin.database.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
