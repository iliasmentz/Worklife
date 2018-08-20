package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,Long> {
}
