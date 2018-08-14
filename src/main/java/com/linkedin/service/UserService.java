package com.linkedin.service;

import com.linkedin.constants.RoleName;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.Role;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.RoleRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.dto.SignUpRequest;
import com.linkedin.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {
	private final LoginRepository loginRepository;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(LoginRepository loginRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.loginRepository = loginRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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

	private Role getUserRole() {
		return roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set."));
	}

	public Login createLogin(SignUpRequest signUpRequest) {
		Login login = new Login(signUpRequest.getEmail(), signUpRequest.getPassword());

		login.setPassword(passwordEncoder.encode(login.getPassword()));
		login.setRoles(Collections.singleton(getUserRole()));

		return loginRepository.save(login);
	}

	public User createUser(Long userId, SignUpRequest signUpRequest) {
		User user = new User();
		user.setId(userId);
		user.setUsername(signUpRequest.getUsername());
		user.setName(signUpRequest.getName());
		user.setSurname(signUpRequest.getSurname());
		user.setBirthdate(signUpRequest.getBirthdate());
		user.setAddress(signUpRequest.getAddress());
		user.setImgPath(signUpRequest.getImgPath());
		user.setPhoneNumber(signUpRequest.getPhoneNumber());
		return userRepository.save(user);
	}
}
