package com.linkedin.controller;

import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.entities.model.connection.ConnectionRequestDto;
import com.linkedin.entities.model.connection.ConnectionStatusDto;
import com.linkedin.service.ConnectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = ConnectionsController.tag)
@RestController
@RequestMapping("/api/")
public class ConnectionsController {
  public static final String tag = "Connections";

  private final ConnectionService connectionService;


  @Autowired
  public ConnectionsController(ConnectionService connectionService) {
	this.connectionService = connectionService;
  }


  @ApiOperation(value = "Return Connections of Another User", notes = "Return Connections of Another User", response = ConnectionDto.class)
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "userId", value = "user's id", required = true, dataType = "Long", example = "1"),
  })
  @GetMapping("/network/connections/{userId}")
  public List<ConnectionDto> getUserConnections(@PathVariable Long userId) throws Exception {
	return connectionService.getUserConnections(userId);
  }

  @ApiOperation(value = "Return Connection Status with  Another User", notes = "0->Connected, 1->Not Connected , 2->UserId has sent Logged User a ConnectionRequest, 3-> Logged User has sent UserId a ConnectionRequest , 4->userId ==loggedUserId", response = ConnectionStatusDto.class)
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "userId", value = "user's id", required = true, dataType = "Long", example = "1"),
  })
  @GetMapping("/network/connections/connectionstatus/{userId}")
  public ConnectionStatusDto getConnectionStatus(@PathVariable Long userId) throws Exception {
	return connectionService.getConnectionStatus(userId);
  }


  @ApiOperation(value = "Get Connections of current loged in User", notes = "Get Connections of current loged in User", response = ConnectionDto.class)
  @GetMapping("/network/connections/")
  public List<ConnectionDto> myConnections() throws Exception {
	return connectionService.getMyConnections();
  }

  //returns All the connection requests that other Users did to the user
  @ApiOperation(value = "returns All the connection requests that other Users did to the user", notes = "returns All the connection requests that other Users did to the user", response = ConnectionRequestDto.class)
  @GetMapping("/network/connections/requests/")
  public List<ConnectionRequestDto> getMyConnectionRequests() throws Exception {
	return connectionService.getMyConnectionRequests();
  }

  @ApiOperation(value = "returns the connectionRequests that user with userid , did to the loged user", notes = "returns the connection request that user with userid  did to the loged user", response = ConnectionRequestDto.class)
  @GetMapping("/network/connections/requests/{userId}")
  public List<ConnectionRequestDto> getConnectionRequestsFromUser(@PathVariable Long userId) throws Exception {

	return connectionService.getConnectionRequestsFromUser(userId);
  }

  @ApiOperation(value = "returns the connectionRequests that loged User all the other Users", notes = "returns the connectionRequests that loged User all the other Users", response = ConnectionRequestDto.class)
  @GetMapping("/network/connections/requests/tousers/")
  public List<ConnectionRequestDto> getConnectionRequestsToOtherUsers() throws Exception {

	return connectionService.getConnectionRequestsToOtherUsers();
  }

  @ApiOperation(value = "Creates a new connection request", notes = "Creates a new connection request", response = ConnectionRequestDto.class)
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "userId", value = "users_id", required = true, dataType = "Long", example = "10"),
  })
  @PostMapping("/network/connections/requests/create/{userId}")
  public ConnectionRequestDto createConnectionRequest(@PathVariable Long userId) throws Exception {
	return connectionService.createNewConnectionRequest(userId);
  }

  @ApiOperation(value = "Accepts a connection request", notes = "Accepts a connection request")
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "connectionRequestId", value = "connectionRequestId", required = true, dataType = "Long", example = "10"),
  })
  @PostMapping("/network/connections/requests/respond/{connectionRequestId}/accept")
  public ConnectionDto acceptToConnectionRequest(@PathVariable Long connectionRequestId) throws Exception {
	return connectionService.acceptToConnectionRequest(connectionRequestId);
  }

  @ApiOperation(value = "Rejects a connection request", notes = "Rejects a connection request")
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "connectionRequestId", value = "connectionRequestId", required = true, dataType = "Long", example = "10"),
  })
  @PostMapping("/network/connections/requests/respond/{connectionRequestId}/reject")
  public void rejectConnectionRequest(@PathVariable Long connectionRequestId) throws Exception {
	connectionService.rejectConnectionRequest(connectionRequestId);
  }


  @ApiOperation(value = "Delete Connection", notes = "Deletes a specific connections", response = Void.class)
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "connectionId", value = "connections Id", required = true, dataType = "Long", example = "152"),
  })
  @DeleteMapping("/network/connections/{connectionId}")
  public void deleteConnection(@PathVariable Long connectionId) throws Exception {
	connectionService.deleteConnection(connectionId);
  }

  @ApiOperation(value = "returns the User that are on the same network with the loged user", notes = "returns the User that are on the same network with the loged user", response = UserSimpleDto.class)
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "userId", value = "users Id", required = true, dataType = "Long", example = "152"),
  })
  @GetMapping("/network/connections/users/{userId}")
  public List<UserSimpleDto> getUsersFriends(@PathVariable Long userId) {
	return connectionService.getFriendsToUserSimpleDto(userId);
  }

    @ApiOperation(value = "returns results of the Users Search", notes = "returns results of the Users Search", response = UserDto.class)
    @GetMapping("/network/connections/users/search/")
    public List<UserDto> getUserSearchResults(@RequestParam("username") String username) {
        return connectionService.getUserSearchResults(username);
    }

}