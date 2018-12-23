package com.linkedin.features.users;

import com.linkedin.config.errors.WrongPasswordException;
import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Login;
import com.linkedin.entities.User;
import com.linkedin.entities.repo.LoginRepository;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.model.UserDto;
import com.linkedin.model.UserRequestDto;
import com.linkedin.model.settings.ChangeEmailRequestDto;
import com.linkedin.model.settings.ChangePasswordRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
  private final LoginRepository loginRepository;
  private final UserRepository userRepository;
  private final UserService userService;
  private final UserConverter userConverter;
  private final PasswordEncoder passwordEncoder;


  @Autowired
  public ProfileService(LoginRepository loginRepository, UserRepository userRepository, UserService userService, UserConverter userConverter, PasswordEncoder passwordEncoder) {
    this.loginRepository = loginRepository;
    this.userRepository = userRepository;
    this.userService = userService;
    this.userConverter = userConverter;
    this.passwordEncoder = passwordEncoder;
  }


  //Returns UserDto
  public UserDto getUserDto(String username) {
    User user = userService.getUser(username);
    Login login = loginRepository.getOne(user.getId());
    UserDto userDto = userConverter.toUserDto(user, login.getRole().ordinal());
    return userDto;
  }


  public UserDto updateProfile(UserRequestDto userRequestDto) {
    Login login = AuthenticationFacade.authenticatedUser();
    User user = userService.getUser(login.getUserId()); //pairnoume ton User


    user.setName(userRequestDto.getName());
    user.setEmail(userRequestDto.getEmail());
    user.setSurname(userRequestDto.getSurname());
    user.setBirthdate(userRequestDto.getBirthdate());
    user.setAddress(userRequestDto.getAddress());
    user.setPhoneNumber(userRequestDto.getPhoneNumber());

    userRepository.save(user);
    return userConverter.toUserDto(user, login.getRole().ordinal());
  }

  public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) throws Exception {
    Login login = AuthenticationFacade.authenticatedUser();

    if (!passwordEncoder.matches(changePasswordRequestDto.getOldPassword(), login.getPassword())) {
      throw new WrongPasswordException();
    }

    login.setPassword(passwordEncoder.encode(changePasswordRequestDto.getNewPassword()));
    loginRepository.save(login);
  }

  public void changeEmail(ChangeEmailRequestDto changeEmailRequestDto) throws Exception {
    User user = userRepository.findById(AuthenticationFacade.authenticatedUser().getUserId()).orElse(null);
    if (userRepository.existsByEmailIgnoreCase(changeEmailRequestDto.getNewEmail())) {
      throw new Exception("Email already exists");
    }
    user.setEmail(changeEmailRequestDto.getNewEmail());
    userRepository.save(user);

  }
}
