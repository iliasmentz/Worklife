package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.ConnectionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long> {
  // List<Connection> findAllByUserRequestedIdOrUserAcceptedId(Long userId1, Long userId2); //ayto epistrefei ola me userId1 == UserRequested h userId2 == UserAccepted
  List<ConnectionRequest> findAllByUserRequestedId(Long userid);// ayto epistrefei ola to connection requests pou stelnei o xrhsths se allous

  List<ConnectionRequest> findAllByUserTargetId(Long userid);// ayto epistrefei ola to connection requests pou exoun steilei alloi ston user

  List<ConnectionRequest> findAllByUserTargetIdAndUserRequestedId(Long userId1, Long userId2);// ayto epistrefei ola to connection requests exei steilei o user2 ston user1
}
