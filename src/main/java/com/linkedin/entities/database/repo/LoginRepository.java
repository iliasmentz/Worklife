package com.linkedin.entities.database.repo;


import com.linkedin.entities.database.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Optional<Login> findByEmail(String email);
	Optional<Login> findByUserId(Long userId);
	boolean existsByEmail(String email);
}
