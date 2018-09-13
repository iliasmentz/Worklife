package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Experience;
import com.linkedin.entities.database.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

  @Query(value = "select e from Experience e where e.userId = :userId")
  List<Experience> findByUserId(@Param("userId")Long userId);
}
