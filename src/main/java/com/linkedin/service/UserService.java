package com.linkedin.service;

import com.linkedin.constants.RoleName;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.model.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final LoginRepository loginRepository;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(LoginRepository loginRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.loginRepository = loginRepository;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User getUser(Long id) {
		return userRepository.getOne(id);
	}

	public boolean usernameTaken(String username) {
		return userRepository.existsByUsername(username);
	}

	public boolean emailExists(String email) {
		return loginRepository.existsByEmail(email);
	}

	private RoleName getUserRole() {
		return RoleName.ROLE_USER;
	}

	public Login createLogin(RegisterRequestDto registerRequestDto) {
		Login login = new Login(registerRequestDto.getEmail(), registerRequestDto.getPassword());

		login.setPassword(passwordEncoder.encode(login.getPassword()));
		login.setRole(getUserRole());

		return loginRepository.save(login);
	}

	public User createUser(Long userId, RegisterRequestDto registerRequestDto) {
		User user = new User();
		user.setId(userId);
		user.setUsername(registerRequestDto.getUsername());
		user.setName(registerRequestDto.getName());
		user.setSurname(registerRequestDto.getSurname());
		user.setBirthdate(registerRequestDto.getBirthdate());
		user.setAddress(registerRequestDto.getAddress());
		user.setImgPath(registerRequestDto.getImgPath());
		user.setPhoneNumber(registerRequestDto.getPhoneNumber());
		return userRepository.save(user);
	}
}
