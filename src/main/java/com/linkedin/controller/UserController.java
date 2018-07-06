package com.linkedin.controller;

import com.linkedin.database.User;
import com.linkedin.database.repo.UserRepository;
import com.linkedin.dto.BaseDto;
import com.linkedin.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/register")
	@ResponseBody
	public BaseDto register(
							@RequestParam String name,
							@RequestParam String surname) {

//		BaseDto baseDto = new BaseDto();
//		if (!password.equals(password2)) {
//			baseDto.getRequestResponse().setStatus(RequestResponse.BAD_REQUEST_STATUS);
//			baseDto.getRequestResponse().setMessage("invalid password");
//			return baseDto;
//		}
//
//		if(loginRepository.findByEmail(email) != null) {
//			BaseDto baseDto = new BaseDto();
//			baseDto.getRequestResponse().setStatus(RequestResponse.BAD_REQUEST_STATUS);
//			baseDto.getRequestResponse().setMessage("Email already used");
//			return baseDto;
//		}

		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		userRepository.save(user);


		UserDto userDto = new UserDto();
		userDto.setUser(user);

		return userDto;
	}
	@PostMapping(name = "/login")
	@ResponseBody
	public BaseDto login(@RequestParam String email,
						 @RequestParam String password) {
//		Login login = loginRepository.findByEmail(email);
//		if (login == null) {
//			BaseDto baseDto = new BaseDto();
//			baseDto.getRequestResponse().setStatus(RequestResponse.UNAUTHORIZED_STATUS);
//			baseDto.getRequestResponse().setMessage("User does not exist");
//			return baseDto;
//		}
//
//		if (!login.getPassword().equals(password)) {
//			BaseDto baseDto = new BaseDto();
//			baseDto.getRequestResponse().setStatus(RequestResponse.UNAUTHORIZED_STATUS);
//			baseDto.getRequestResponse().setMessage("Invalid Password");
//			return baseDto;
//		}
//
		UserDto userDto = new UserDto();
//		userDto.setLogin(login);
//		userDto.setUser(userRepository.getOne(login.getUserId()));
		return userDto;
	}

}
