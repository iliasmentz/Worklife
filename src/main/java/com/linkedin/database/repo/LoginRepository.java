package com.linkedin.database.repo;


import com.linkedin.database.Login;
import com.linkedin.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long> {

}
