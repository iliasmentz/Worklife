package com.linkedin.controller;

import com.linkedin.constants.UrlConst;
import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.model.RegisterRequestDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserRequestDto;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = AuthController.tag)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	public static final String tag = "Authentication";

	private final UserService userService;
	private final UserConverter userConverter;

	@Autowired
	public AuthController(UserService userService, UserConverter userConverter) {
		this.userService = userService;
		this.userConverter = userConverter;
	}

	@ApiOperation(value = "Register", notes = "Creates a new user", response = String.class)
	@PostMapping(UrlConst.REGISTER)
	public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
		if (userService.usernameTaken(registerRequestDto.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		if (userService.emailExists(registerRequestDto.getEmail())) {
			return new ResponseEntity<>("Email Address already in use!", HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		userService.register(registerRequestDto);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	@GetMapping("/profile")
	@ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
	public UserDto myProfile() {
		Login login = AuthenticationFacade.authenticatedUser();
		User user = userService.getUser(login.getUserId());
		return userConverter.toUserDto(user);
	}

	@PutMapping("/profile")
	public UserDto updateProfile(@RequestBody UserRequestDto userRequestDto) {
		Login login = AuthenticationFacade.authenticatedUser();
		User user = userService.getUser(login.getUserId());
		userRequestDto.updateUser(user);
		user = userService.save(user);
		return userConverter.toUserDto(user);
	}

	@GetMapping("/profile/{username}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "username", value = "user's username", required = true, dataType = "string", example = "johndoe"),
	})
	@ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
	public UserDto getProfile(@PathVariable String username) {
		User user = userService.getUser(username);
		return userConverter.toUserDto(user);
	}
}

