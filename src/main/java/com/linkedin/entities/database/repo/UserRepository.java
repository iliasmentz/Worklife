package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameIgnoreCase(String username);

	boolean existsByEmailIgnoreCase(String email);
}
