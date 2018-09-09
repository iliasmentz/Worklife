package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
}
