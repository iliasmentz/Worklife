package com.linkedin.controller;

import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.User;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.ConnectionService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = ConnectionsController.tag)
@RestController
@RequestMapping("/api/auth")
public class ConnectionsController {
	public static final String tag = "Connections";

	private final UserService userService;
	private final ConnectionService connectionService;

	private final UserConverter userConverter;

	@Autowired
	public ConnectionsController(UserService userService, ConnectionService connectionService, UserConverter userConverter) {
		this.userService = userService;
		this.connectionService = connectionService;
		this.userConverter = userConverter;
	}

	/*@ApiOperation(value = "Get Connections", notes = "Return's connections of a user", response = UserDto.class)
	@ApiImplicitParams({
					@ApiImplicitParam(name = "userId", value = "user's id", required = true, dataType = "long", example = "1"),
	})
	@GetMapping("/network/connections/{userId}")
	public List<UserDto> myProfile(@PathVariable Long userId) throws Exception {
		if (!userService.isUserExists(userId)) {
			throw new ObjectNotFoundException(User.class, userId);
		}
		return connectionService.getUserConnections(userId)
						.stream()
						.map(userConverter::toUserDto)
						.collect(Collectors.toList());
	}*/

  @ApiOperation(value = "Return Connections of Another User", notes = "Return Connections of Another User", response = ConnectionDto.class)
  @ApiImplicitParams({
	  @ApiImplicitParam(name = "userId", value = "user's id", required = true, dataType = "Long", example = "1"),
  })
  @GetMapping("/network/connections/{userId}")
  public List<ConnectionDto> getUserConnections(@PathVariable Long userId) throws Exception {
	return connectionService.getUserConnections(userId);
  }


  @ApiOperation(value = "Get Connections of current loged in User", notes = "Get Connections of current loged in User", response = ConnectionDto.class)
  @GetMapping("/network/connections/")
  public List<ConnectionDto> myProfile() throws Exception {
	return connectionService.getMyConnections();
  }

/*	@ApiOperation(value = "Request Connection", notes = "Creates a new connection request", response = Void.class)
	@ApiImplicitParams({
					@ApiImplicitParam(name = "username", value = "user's username", required = true, dataType = "String", example = "johnDoe"),
	})
	@PostMapping("/network/connections/{username}")
	public void createConnectionRequest(@PathVariable String username) {
		User requestUserId = getConnectedUser();
		User targetUserId = userService.getUser(username);
		connectionService.requestConnection(requestUserId, targetUserId);
	}

	@ApiOperation(value = "Delete Connection", notes = "Deletes a specific connections", response = Void.class)
	@ApiImplicitParams({
					@ApiImplicitParam(name = "connectionId", value = "connections Id", required = true, dataType = "Long", example = "152"),
	})
	@DeleteMapping("/network/connections/{connectionId}")
	public void createConnectionRequest(@PathVariable Long connectionId) throws Exception {
		connectionService.deleteConnection(connectionId, AuthenticationFacade.getUserId());
	}

	private User getConnectedUser() {
		return userService.getUser(AuthenticationFacade.getUserId());
	}*/

}
