package com.linkedin.service;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.ConnectionRequest;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.ConnectionRepository;
import com.linkedin.entities.database.repo.ConnectionRequestRepository;
import com.linkedin.errors.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {
	private final ConnectionRepository connectionRepository;
	private final ConnectionRequestRepository connectionRequestRepository;

	@Autowired
	public ConnectionService(ConnectionRepository connectionRepository, ConnectionRequestRepository connectionRequestRepository) {
		this.connectionRepository = connectionRepository;
		this.connectionRequestRepository = connectionRequestRepository;
	}

	public List<User> getUserConnections(Long userId) {
		return connectionRepository.getUsersConnections(userId)
						.stream()
						.map(x -> x.getUserAccepted().getId().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
						.collect(Collectors.toList());
	}

	public ConnectionRequest requestConnection(User userRequesting, User targetUser) {
		ConnectionRequest connectionRequest = new ConnectionRequest();
		connectionRequest.setUserRequested(userRequesting);
		connectionRequest.setUserTarget(targetUser);
		connectionRequest.setDateOfRequest(new Date());
		connectionRequest.setCompleted(false);
		return connectionRequestRepository.save(connectionRequest);
	}

	public void deleteConnection(Long connectionId, Long userId) throws Exception {
		Connection connection = connectionRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(Connection.class, connectionId));
		if (!isUsersConnection(userId, connection)) {
			throw new IllegalAccessException("not yours connection");
		}
		connectionRepository.delete(connection);
	}

	private boolean isUsersConnection(Long userId, Connection connection) {
		return connection.getUserAccepted().getId().equals(userId) || connection.getUserRequested().getId().equals(userId);
	}
}
