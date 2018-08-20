package com.linkedin.entities.database.repo;



import com.linkedin.entities.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT * FROM 'users'")
//    List<User> findAll();


}