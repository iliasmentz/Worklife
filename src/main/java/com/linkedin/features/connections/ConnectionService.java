package com.linkedin.features.connections;

import com.linkedin.config.errors.ObjectNotFoundException;
import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Connection;
import com.linkedin.entities.ConnectionRequest;
import com.linkedin.entities.Login;
import com.linkedin.entities.User;
import com.linkedin.entities.repo.ConnectionRepository;
import com.linkedin.entities.repo.ConnectionRequestRepository;
import com.linkedin.entities.repo.LoginRepository;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.features.notifications.NotificationService;
import com.linkedin.features.users.UserConverter;
import com.linkedin.model.UserDto;
import com.linkedin.model.UserSimpleDto;
import com.linkedin.model.connection.ConnectionDto;
import com.linkedin.model.connection.ConnectionRequestDto;
import com.linkedin.model.connection.ConnectionStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConnectionService {
  private final ConnectionRepository connectionRepository;
  private final ConnectionRequestRepository connectionRequestRepository;
  private final ConnectionConverter connectionConverter;
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final NotificationService notificationService;
  private final LoginRepository loginRepository;

  @Autowired
  public ConnectionService(ConnectionRepository connectionRepository, ConnectionRequestRepository connectionRequestRepository, ConnectionConverter connectionConverter, UserRepository userRepository, UserConverter userConverter,
      NotificationService notificationService, LoginRepository loginRepository) {
    this.connectionRepository = connectionRepository;
    this.connectionRequestRepository = connectionRequestRepository;
    this.connectionConverter = connectionConverter;
    this.userRepository = userRepository;

    //this.connectionRequestDto = connectionRequestDto;
    this.userConverter = userConverter;
    this.notificationService = notificationService;
    this.loginRepository = loginRepository;
  }

  public void deleteConnection(Long connectionId) throws Exception {
    Long userId = AuthenticationFacade.getUserId();
    Connection connection = connectionRepository.findById(connectionId).orElseThrow(() -> new ObjectNotFoundException(Connection.class, connectionId));
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
        .map(connectionConverter::toConnectionRequestDto)
        .filter(x -> x.getStatus() == 0)
        .collect(Collectors.toList());
  }

  //returns the connectionRequests that user with userid  did to the loged user
  public List<ConnectionRequestDto> getConnectionRequestsFromUser(Long userId) {
    return connectionRequestRepository.findAllByUserTargetIdAndUserRequestedId(userId, userId)
        .stream()
        .map(connectionConverter::toConnectionRequestDto)
        .filter(x -> x.getStatus() == 0)
        .collect(Collectors.toList());
  }


  //logged User makes a Request to another user with Id userId
  public ConnectionRequestDto createNewConnectionRequest(Long userId) throws Exception {
    if (!userRepository.existsById(userId)) {
      throw new ObjectNotFoundException(User.class, userId);
    }
    ConnectionRequest connectionRequest = new ConnectionRequest();
    Long loggedUserId = AuthenticationFacade.authenticatedUser().getUserId();
    //an o loggedUser exei kanei hdh connection Request na mhn ton ksanaafhsei dhladh na mhn kanei tpt
    if (connectionRequestRepository.findAllByUserTargetIdAndUserRequestedId(userId, loggedUserId).size() > 0) {
      return null;
    }

    connectionRequest.setDateOfRequest(new Date());
    connectionRequest.setStatus(0); //pending to start with
    connectionRequest.setUserRequestedId(loggedUserId);
    connectionRequest.setUserTargetId(userId);

    connectionRequestRepository.save(connectionRequest);
    notificationService.createNotification(userId, 2, connectionRequest.getConnectionRequestId());

    return connectionConverter.toConnectionRequestDto(connectionRequest);

  }

  public ConnectionDto acceptToConnectionRequest(Long connectionRequestId) throws Exception {
    if (!connectionRequestRepository.existsById(connectionRequestId)) {
      throw new ObjectNotFoundException(ConnectionRequest.class, connectionRequestId);
    }
    ConnectionRequest connectionRequest = connectionRequestRepository.findById(connectionRequestId).orElse(null);
    connectionRequest.setStatus(1); //accept
    connectionRequestRepository.save(connectionRequest);

    //apothikebw sto connections

    Connection connection = new Connection();
    connection.setConnectionRequestId(connectionRequestId);
    connection.setCreateDate(connectionRequest.getDateOfRequest());
    connection.setUserAcceptedId(connectionRequest.getUserTargetId());
    connection.setUserRequestedId(connectionRequest.getUserRequestedId());
    connectionRepository.save(connection);
    return connectionConverter.toConnectionDto(connection);

  }

  public void rejectConnectionRequest(Long connectionRequestId) throws Exception {
    if (!connectionRequestRepository.existsById(connectionRequestId)) {
      throw new ObjectNotFoundException(ConnectionRequest.class, connectionRequestId);
    }

    ConnectionRequest connectionRequest = connectionRequestRepository.findById(connectionRequestId).orElse(null);
    connectionRequest.setStatus(2); //reject
    connectionRequestRepository.save(connectionRequest);
  }

  //epistrefei lista apo Users pou einai connected me ton User mas
  private List<User> getFriends(Long userId) {
    List<Connection> connections = connectionRepository.findAllByUserRequestedIdOrUserAcceptedId(userId, userId);
    List<User> userList = new ArrayList<>();

    for (Connection connection : connections) {
      if (!connection.getUserRequestedId().equals(userId) && connection.getUserAcceptedId().equals(userId)) {
        userList.add(userRepository.findById(connection.getUserRequestedId()).orElse(null));
      } else if (connection.getUserRequestedId().equals(userId) && !connection.getUserAcceptedId().equals(userId)) {
        userList.add(userRepository.findById(connection.getUserAcceptedId()).orElse(null));
      }
    }
    return userList;
  }

  public List<UserSimpleDto> getFriendsToUserSimpleDto(Long userId) {
    List<UserSimpleDto> userDtoList = new ArrayList<>();
    List<User> userList = getFriends(userId);
    for (User anUserList : userList) {
      if (anUserList != null) {
        userDtoList.add(userConverter.toUserSimpleDto(anUserList));
      }

    }
    return userDtoList;
  }

  public List<ConnectionRequestDto> getConnectionRequestsToOtherUsers() {
    Long loggedUserId = AuthenticationFacade.authenticatedUser().getUserId();
    return connectionRequestRepository.findAllByUserRequestedId(loggedUserId).stream().map(connectionConverter::toConnectionRequestDto).collect(Collectors.toList());
  }

  public ConnectionStatusDto getConnectionStatus(Long userId) throws Exception {
    if (!userRepository.existsById(userId)) {
      throw new ObjectNotFoundException(User.class, userId);
    }
    Long loggedUserId = AuthenticationFacade.authenticatedUser().getUserId();
    ConnectionStatusDto connectionStatusDto = new ConnectionStatusDto();
    //myprofile
    if (loggedUserId.equals(userId)) {
      connectionStatusDto.setConnectionStatus(4);
      return connectionStatusDto;
    }
    if (connectionRepository.existsByUserAcceptedIdAndUserRequestedId(userId, loggedUserId) || connectionRepository.existsByUserAcceptedIdAndUserRequestedId(loggedUserId, userId)) {
      //an einai connected
      connectionStatusDto.setConnectionStatus(0);
    } else if (connectionRequestRepository.findAllByUserTargetIdAndUserRequestedId(loggedUserId, userId).size() > 0) {
      //an exei steilei o allos se emena pou eimai logedin
      connectionStatusDto.setConnectionStatus(2);
    } else if (connectionRequestRepository.findAllByUserTargetIdAndUserRequestedId(userId, loggedUserId).size() > 0) {
      //an tou exw steilei egw connectionRequest
      connectionStatusDto.setConnectionStatus(3);
    } else {
      //an den einai filoi
      connectionStatusDto.setConnectionStatus(1);
    }
    return connectionStatusDto;
  }


  public List<UserDto> getUserSearchResults(String username) {
    List<User> users = userRepository.searchUser(username);
    return users.stream()
        .map(x -> {
          Login login = loginRepository.findByUserId(x.getId()).orElse(null);
          Integer role = login != null ? login.getRole().ordinal() : 0;
          return userConverter.toUserDto(x, role);
        }).collect(Collectors.toList());
  }
}
