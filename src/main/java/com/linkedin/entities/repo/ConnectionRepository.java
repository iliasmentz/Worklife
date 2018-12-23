package com.linkedin.entities.repo;

import com.linkedin.entities.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {

  List<Connection> findAllByUserRequestedIdOrUserAcceptedId(Long userId1, Long userId2); //ayto epistrefei ola me userId1 == UserRequested h userId2 == UserAccepted

  List<Connection> findAllByUserRequestedId(Long userId);

  List<Connection> findAllByUserAcceptedId(Long userId);

  Connection findByUserRequestedIdAndUserAcceptedId(Long userId1, Long userId2);

  Boolean existsByUserAcceptedIdAndUserRequestedId(Long userId1, Long userId2);
}
