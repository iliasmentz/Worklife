package com.linkedin.controller;


import com.linkedin.constants.UrlConst;
import com.linkedin.entities.database.Login;
import com.linkedin.model.ApiResponse;
import com.linkedin.model.RegisterRequestDto;
import com.linkedin.model.UserDto;
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

	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
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
		Login result = userService.createLogin(registerRequestDto);
		userService.createUser(result.getUserId(), registerRequestDto);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

	@GetMapping("/profile")
	@ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
	public UserDto myProfile() {
		Login login = AuthenticationFacade.authenticatedUser();
		return new UserDto(login, userService.getUser(login.getUserId()));
	}

	@GetMapping("/profile/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "long", example = "1234"),
	})
	@ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
	public UserDto myProfile(@PathVariable Long id) {
		return new UserDto(userService.getUser(id));
	}

	@GetMapping("/test")
	public ApiResponse test() {
		return new ApiResponse("Test Coble");
	}

}

