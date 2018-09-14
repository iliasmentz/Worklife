package com.linkedin.entities.database.repo;


import com.linkedin.entities.database.Education;
import com.linkedin.entities.database.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

  @Query(value = "select e from Skill e where e.userId = :userId")
  List<Skill> findByUserId(@Param("userId")Long userId);
}
