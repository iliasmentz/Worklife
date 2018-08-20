package com.linkedin.entities.database.repo;


import com.linkedin.entities.database.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

}
