package com.linkedin.controller;

import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserRequestDto;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.ProfileService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = ProfileController.tag)
@RestController
@RequestMapping("/api/profile/")
public class ProfileController {

    public  static final  String tag = "Profile Controller";

    private final UserService userService;
    private final ProfileService profileService;
    private final UserConverter userConverter;
    private final UserRepository userRepository;

    @Autowired
    public ProfileController(UserService userService, ProfileService profileService, UserConverter userConverter, UserRepository userRepository){
        this.userService = userService;
        this.profileService = profileService;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @GetMapping("/profile")
    @ApiOperation(value = "Profile", notes = "Returns User's profile info", response = UserDto.class)
    public UserDto myProfile() {
        Login login = AuthenticationFacade.authenticatedUser();
        System.out.println("\n\n\n\n");
        System.out.println(login.getUserId());
        System.out.println("\n\n\n\n");
        userRepository.findById(1L);
        return userConverter.toUserDto( userService.getUser(login.getUserId()));
       // return new UserDto(userService.getUser(login.getUserId()));
    }

    @PutMapping("/profile")
    @ApiOperation(value = "Profile", notes = "Changes User's profile info", response = UserDto.class)
    public UserDto updateProfile(@RequestBody UserRequestDto userRequestDto) {

        //userService.emailExists();

        profileService.updateProfile(userRequestDto);
        return null;

    }





    @GetMapping("/profile/{username}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "user's username", required = true, dataType = "string", example = "johndoe"),
    })
    @ApiOperation(value = "Profile", notes = "Returns profile's info", response = UserDto.class)
    public UserDto getProfile(@PathVariable String username) {

       return profileService.getUserDto(username);

    }
}
