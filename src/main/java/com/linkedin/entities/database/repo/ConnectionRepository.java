package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

	@Query(value = "select c from Connection c where c.userRequested.id = :userId or c.userAccepted.id = :userId")
	public List<Connection> getUsersConnections(@Param("userId")Long userId);

}
