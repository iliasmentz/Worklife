package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {

    @Query(value = "select c from Education e where e.userId = :userId ")
    public List<Education> getUserEducation(@Param("userId")Long userId);

}
