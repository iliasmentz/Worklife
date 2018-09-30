package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.User;
import com.linkedin.entities.model.recommendation.UserInfoBo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameIgnoreCase(String username);

	@Query("SELECT u FROM User u WHERE lower(u.username) LIKE CONCAT('%',lower(:term),'%') "
			+ "OR lower(u.name) LIKE CONCAT('%',lower(:term),'%') "
			+ "OR lower(u.surname) LIKE CONCAT('%',lower(:term),'%')")
	List<User> searchUser(@Param("term") String term);

	boolean existsByEmailIgnoreCase(String email);

	@Query(name = "getUsersVectorQuery", nativeQuery = true)
	Collection<UserInfoBo> getAllUsersVector();

	@Query(value = "select u.user_id from users u", nativeQuery = true)
	List<Long> getAllUsersIds();
}
