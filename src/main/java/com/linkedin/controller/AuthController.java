package com.linkedin.controller;

import com.linkedin.constants.UrlConst;
import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.RegisterDto;
import com.linkedin.entities.model.RegisterRequestDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = AuthController.tag)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	public static final String tag = "Authentication";

	private final UserService userService;
	private final UserConverter userConverter;
	private final UserRepository userRepository;

	@Autowired
	public AuthController(UserService userService, UserConverter userConverter, UserRepository userRepository) {
		this.userService = userService;
		this.userConverter = userConverter;
		this.userRepository = userRepository;
	}

	@ApiOperation(value = "Register", notes = "Creates a new user", response = String.class)
	@PostMapping(UrlConst.REGISTER)
	public RegisterDto registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
		if (userService.usernameTaken(registerRequestDto.getUsername())) {
			return new RegisterDto("Username is already taken!");
		}

		if (userService.emailExists(registerRequestDto.getEmail())) {
			return new RegisterDto("Email Address already in use!");
		}

		// Creating user's account
		userService.register(registerRequestDto);

		return new RegisterDto("User registered successfully");
	}

	@ApiOperation(value = "Returns Users", notes = "Returns all Users", response = String.class)
	@GetMapping("/users")
	public List<UserDto> registerUser() {

		return userRepository.findAll()
				.stream()
				.map(userConverter::toUserDto)
				.collect(Collectors.toList());

	}
}

