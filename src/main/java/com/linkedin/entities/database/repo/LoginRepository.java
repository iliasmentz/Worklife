package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Login findByUsernameIgnoreCase(String username);

	Optional<Login> findByUserId(Long userId);

	boolean existsByUsernameIgnoreCase(String email);
}
