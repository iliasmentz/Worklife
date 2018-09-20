package com.linkedin.service;

import com.linkedin.converter.ConnectionConverter;
//import com.linkedin.entities.database.ConnectionRequest;
import com.linkedin.converter.ConnectionRequestConverter;
import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.ConnectionRepository;
import com.linkedin.entities.database.repo.ConnectionRequestRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.entities.model.connection.ConnectionRequestDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {
  private final ConnectionRepository connectionRepository;
  private final ConnectionRequestRepository connectionRequestRepository;
  private final ConnectionConverter connectionConverter;
  private final ConnectionRequestConverter connectionRequestConverter;
  private final UserRepository userRepository;

  @Autowired
  public ConnectionService(ConnectionRepository connectionRepository, ConnectionRequestRepository connectionRequestRepository, ConnectionConverter connectionConverter, ConnectionRequestConverter connectionRequestConverter, UserRepository userRepository) {
	this.connectionRepository = connectionRepository;
	this.connectionRequestRepository = connectionRequestRepository;
	this.connectionConverter = connectionConverter;
	this.connectionRequestConverter = connectionRequestConverter;
	this.userRepository = userRepository;
  }

//	public List<ConnectionDto> getUserConnections(Long userId) {
//		return connectionRepository.findAllByUserRequested(userId)
//						.stream()
//						//.map(x -> x.getUserAccepted().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
//						.map(x -> x.getUserAcceptedId().equals(userId) ? connectionConverter.toConnectionDto(x) )
//						.collect(Collectors.toList());
//	}
//
//	public ConnectionRequest requestConnection(User userRequesting, User targetUser) {
//		ConnectionRequest connectionRequest = new ConnectionRequest();
//		connectionRequest.setUserRequested(userRequesting);
//		connectionRequest.setUserTarget(targetUser);
//		connectionRequest.setDateOfRequest(new Date());
//		connectionRequest.setCompleted(false);
//		return connectionRequestRepository.save(connectionRequest);
//	}
//

  public void deleteConnection(Long connectionId) throws Exception {
	Long userId = AuthenticationFacade.getUserId();
	Connection connection = connectionRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(Connection.class, connectionId));
	if (!isUsersConnection(userId, connection)) {
	  throw new IllegalAccessException("not yours connection");
	}
	connectionRepository.delete(connection);
  }

  private boolean isUsersConnection(Long userId, Connection connection) {
	return connection.getUserAcceptedId().equals(userId) || connection.getUserRequestedId().equals(userId);
  }


  //Gyrna ta Connection tou User pou einai loged in twra
  public List<ConnectionDto> getMyConnections() {
	Long userId = AuthenticationFacade.authenticatedUser().getUserId();

	return connectionRepository.findAllByUserRequestedIdOrUserAcceptedId(userId, userId)
		.stream()
		//.map(x -> x.getUserAccepted().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
		.map(connectionConverter::toConnectionDto)
		.collect(Collectors.toList());
  }
  //Gyrna ta Connection tou tou User me id userid

  public List<ConnectionDto> getUserConnections(Long userId) throws Exception {
	if (!userRepository.existsById(userId)) {
	  throw new ObjectNotFoundException(User.class, userId);
	}
	return connectionRepository.findAllByUserRequestedIdOrUserAcceptedId(userId, userId)
		.stream()
		//.map(x -> x.getUserAccepted().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
		.map(connectionConverter::toConnectionDto)
		.collect(Collectors.toList());

  }

  //returns All the connection requests that other Users did to the user
  public List<ConnectionRequestDto> getMyConnectionRequests() {
	Long userId = AuthenticationFacade.authenticatedUser().getUserId();
	return connectionRequestRepository.findAllByUserTargetId(userId)
		.stream()
		.map(connectionRequestConverter::toConnectionRequestDto)
		.collect(Collectors.toList());


  }

  //returns the connectionRequests that user with userid  did to the loged user
  public List<ConnectionRequestDto> getConnectionRequestsFromUser(Long userId) {
	Long logedInUserId = AuthenticationFacade.authenticatedUser().getUserId();
//	return connectionRequestRepository.findAllByUserTargetIdAAndUserRequestedId(logedInUserId, userId)
//		.stream()
//		.map(connectionRequestConverter::toConnectionRequestDto)
//		.collect(Collectors.toList());
return null;
  }

  	//logged User creates a new ConnectionRequest with the 
	public ConnectionRequestDto createNewConnectionRequest(Long userId) {
	}
}
