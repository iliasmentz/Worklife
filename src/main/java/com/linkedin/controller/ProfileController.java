package com.linkedin.controller;

import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.model.UploadFileResponse;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserRequestDto;
import com.linkedin.entities.model.changePasswordEmail.ChangeEmailRequestDto;
import com.linkedin.entities.model.changePasswordEmail.ChangePasswordRequestDto;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.ProfileService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Api(tags = ProfileController.tag)
@RestController
@RequestMapping("/api/profile/")
public class ProfileController {

  public static final String tag = "Profile Controller";

  private final UserService userService;
  private final ProfileService profileService;
  private final UserConverter userConverter;

  @Autowired
  public ProfileController(UserService userService, ProfileService profileService, UserConverter userConverter) {
	this.userService = userService;
	this.profileService = profileService;
	this.userConverter = userConverter;
  }

  @GetMapping("/")
  @ApiOperation(value = "Profile", notes = "Returns User's profile info", response = UserDto.class)
  public UserDto myProfile() {
	Login login = AuthenticationFacade.authenticatedUser();

	return userConverter.toUserDto(userService.getUser(login.getUserId()));
	// return new UserDto(userService.getUser(login.getUserId()));
  }

  @PutMapping("/")
  @ApiOperation(value = "Profile", notes = "Changes User's profile info", response = UserDto.class)
  public UserDto updateProfile(@RequestBody UserRequestDto userRequestDto) {

	//userService.emailExists();

	return profileService.updateProfile(userRequestDto);
  }

  @GetMapping("/{username}")
  @ApiImplicitParams( {
	  @ApiImplicitParam(name = "username", value = "user's username", required = true, dataType = "string", example = "johndoe"),
  })
  @ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
  public UserDto getProfile(@PathVariable String username) {

	return profileService.getUserDto(username);
  }

  @PostMapping("/upload_photo")
  @ApiOperation(value = "Upload photo", notes = "Uploads users photo", response = UploadFileResponse.class)
  public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	UploadFileResponse uploadFileResponse = userService.savePhoto(file);
	return uploadFileResponse;
  }

  @PutMapping("/change-pass/")
  @ApiOperation(value = "change password ", notes = "changes password")
  public void changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto) throws Exception {
	profileService.changePassword(changePasswordRequestDto);
  }

  @PutMapping("/change-email/")
  @ApiOperation(value = "change email ", notes = "changes email")
  public void changeEmail(@RequestBody ChangeEmailRequestDto changeEmailRequestDto) throws Exception {
	profileService.changeEmail(changeEmailRequestDto);
  }


}
