package com.linkedin.features.users;

import com.linkedin.config.constants.UrlConst;
import com.linkedin.entities.repo.LoginRepository;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.model.RegisterRequestDto;
import com.linkedin.model.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  private final LoginRepository loginRepository;

  @Autowired
  public AuthController(UserService userService, UserConverter userConverter, UserRepository userRepository, LoginRepository loginRepository) {
    this.userService = userService;
    this.userConverter = userConverter;
    this.userRepository = userRepository;
    this.loginRepository = loginRepository;
  }

  @ApiOperation(value = "Register", notes = "Creates a new user", response = String.class)
  @PostMapping(UrlConst.REGISTER)
  public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
    if (userService.usernameTaken(registerRequestDto.getUsername())) {
      return new ResponseEntity<>("Username is already taken!", HttpStatus.NOT_ACCEPTABLE);
    }

    if (userService.emailExists(registerRequestDto.getEmail())) {
      return new ResponseEntity<>("Email Address already in use!", HttpStatus.NOT_ACCEPTABLE);
    }

    // Creating user's account
    userService.register(registerRequestDto);

    return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
  }

  @ApiOperation(value = "Returns Users", notes = "Returns all Users", response = String.class)
  @GetMapping("/users")
  public List<UserDto> registerUser() {

    return userRepository.findAll()
        .stream()
        .map(user -> userConverter.toUserDto(user, loginRepository.getOne(user.getId()).getRole().ordinal()))
        .collect(Collectors.toList());

  }
}

