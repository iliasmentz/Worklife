package com.linkedin.entities.database.repo;


import com.linkedin.entities.database.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
