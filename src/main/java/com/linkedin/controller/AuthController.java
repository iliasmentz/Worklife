package com.linkedin.controller;


import com.linkedin.constants.UrlConst;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.dto.LoginRequestDto;
import com.linkedin.entities.dto.SignUpRequest;
import com.linkedin.payload.ApiResponse;
import com.linkedin.payload.LoginDto;
import com.linkedin.security.JwtTokenProvider;
import com.linkedin.security.UserPrincipal;
import com.linkedin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	public static final String tag = "";

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	private final UserService userService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userService = userService;
	}

	@PostMapping(UrlConst.LOGIN)
	public ResponseEntity<LoginDto> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDto.getEmail(),
						loginRequestDto.getPassword()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		Login login = ((UserPrincipal) authentication.getPrincipal()).toLogin();
		User user = userService.getUser(login.getUserId());
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new LoginDto(jwt, user));
	}

	@PostMapping(UrlConst.REGISTER)
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userService.usernameTaken(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (userService.emailExists(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		Login result = userService.createLogin(signUpRequest);
		User user = userService.createUser(result.getUserId(), signUpRequest);

		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(user.getUsername()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}

	@GetMapping("/test")
	@PreAuthorize("hasRole('USER')")
	public ApiResponse test() {
		return new ApiResponse(true, "Test Coble");
	}

}

