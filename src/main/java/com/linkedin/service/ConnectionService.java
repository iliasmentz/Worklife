package com.linkedin.service;

import com.linkedin.converter.ConnectionConverter;
//import com.linkedin.entities.database.ConnectionRequest;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.ConnectionRepository;
import com.linkedin.entities.database.repo.ConnectionRequestRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.connection.ConnectionDto;
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
	private final UserRepository userRepository;

	@Autowired
	public ConnectionService(ConnectionRepository connectionRepository, ConnectionRequestRepository connectionRequestRepository, ConnectionConverter connectionConverter, UserRepository userRepository) {
		this.connectionRepository = connectionRepository;
		this.connectionRequestRepository = connectionRequestRepository;
	  this.connectionConverter = connectionConverter;
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
//	public void deleteConnection(Long connectionId, Long userId) throws Exception {
//		Connection connection = connectionRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(Connection.class, connectionId));
//		if (!isUsersConnection(userId, connection)) {
//			throw new IllegalAccessException("not yours connection");
//		}
//		connectionRepository.delete(connection);
//	}
//
//	private boolean isUsersConnection(Long userId, Connection connection) {
//		return connection.getUserAcceptedId().equals(userId) || connection.getUserRequested().getId().equals(userId);
//	}


	//Gyrna ta Connection tou User pou einai loged in twra
  public  List<ConnectionDto> getMyConnections() {
	Long userId = AuthenticationFacade.authenticatedUser().getUserId();

	return connectionRepository.findAllByUserRequestedIdOrUserAcceptedId(userId,userId)
		.stream()
		//.map(x -> x.getUserAccepted().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
		.map(connectionConverter::toConnectionDto)
		.collect(Collectors.toList());
  }
  //Gyrna ta Connection tou tou User me id userid

  public List<ConnectionDto> getUserConnections(Long userId) throws  Exception {
	  if(!userRepository.existsById(userId)){
	    throw new ObjectNotFoundException(User.class, userId);
	  }
	return connectionRepository.findAllByUserRequestedIdOrUserAcceptedId(userId,userId)
		.stream()
		//.map(x -> x.getUserAccepted().equals(userId) ? x.getUserRequested() : x.getUserAccepted())
		.map(connectionConverter::toConnectionDto)
		.collect(Collectors.toList());

  }
}
