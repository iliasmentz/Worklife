package com.linkedin.service;

import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserRequestDto;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserConverter userConverter;

    @Autowired
    public ProfileService(LoginRepository loginRepository, UserRepository userRepository, UserService userService, UserConverter userConverter) {
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.userConverter = userConverter;
    }


    //Returns UserDto
    public UserDto getUserDto(String username){
        User user = userService.getUser(username);
        UserDto userDto = userConverter.toUserDto(user);
        return userDto;
    }


    public  UserDto updateProfile(UserRequestDto userRequestDto) {
        Login login = AuthenticationFacade.authenticatedUser();
        User user = userService.getUser(login.getUserId()); //pairnoume ton User



        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setBirthdate(userRequestDto.getBirthdate());
        user.setAddress(userRequestDto.getAddress());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setImgPath(userRequestDto.getImgPath());

        userRepository.save(user);
        return userConverter.toUserDto(user);
    }
}
