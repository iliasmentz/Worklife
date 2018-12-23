package com.linkedin.entities.repo;

import com.linkedin.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

  @Query(value = "select e from Experience e where e.userId = :userId")
  List<Experience> findByUserId(@Param("userId") Long userId);

  List<Experience> findAllByUserIdOrderByStartDateDesc(Long userId);

}
