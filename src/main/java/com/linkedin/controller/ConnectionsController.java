package com.linkedin.controller;

import com.linkedin.entities.database.User;
import com.linkedin.entities.model.UserDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = ConnectionsController.tag)
@RestController
@RequestMapping("/api/auth")
public class ConnectionsController {
	public static final String tag = "Connections";

	private final UserService userService;

	@Autowired
	public ConnectionsController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/network/connections/{userId}")
	@ApiImplicitParams({
					@ApiImplicitParam(name = "userId", value = "user's id", required = true, dataType = "Long", example = "1"),
	})
	@ApiOperation(value = "Profile", notes = "Return's connections of a user", response = UserDto.class)
	public List<UserDto> myProfile(@PathVariable Long userId) throws Exception {
		User user = userService.getUser(userId);
		if (user == null) {
			throw new ObjectNotFoundException(User.class, userId);
		}

		UserDto userDto = new UserDto(user);
		List<UserDto> userDtos = new ArrayList<>();
		userDtos.add(userDto);
		return userDtos;
	}

}
