package com.linkedin.controller;

import com.linkedin.database.User;
import com.linkedin.database.repo.UserRepository;
import com.linkedin.dto.BaseDto;
import com.linkedin.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
	@Autowired
	private static UserRepository userRepository;

	//@Autowired
	//public UserController(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}

	@PostMapping("/register")
	public  @ResponseBody User register(
							@RequestParam String email,@RequestParam String username,
							@RequestParam String password) {
		System.out.println("email = ");
		System.out.println(email);



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

		User user = new User(email,username,password);
//		user.setEmail(email);
//		user.setPassword(password);
		userRepository.save(user);
		user.printUser();
		System.out.println(user);

//		UserDto userDto = new UserDto();
//		userDto.setUser(user);

		return user;
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

	@GetMapping(name = "/login")
	public String login() {

		return "<h1>Hallo from login</h1>";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
