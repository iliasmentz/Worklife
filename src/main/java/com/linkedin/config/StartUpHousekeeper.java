package com.linkedin.config;

import com.linkedin.constants.RoleName;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.Role;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.RoleRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

@Component
public class StartUpHousekeeper {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final LoginRepository loginRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public StartUpHousekeeper(RoleRepository roleRepository, UserRepository userRepository, LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.loginRepository = loginRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() throws ParseException {
		// do whatever you need here
		roleRepository.save(new Role(RoleName.ROLE_USER));
		roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		createAdmin();
	}

	private void createAdmin() throws ParseException {

		Login login = new Login("admin@admin.com", "123456");
		login.setPassword(passwordEncoder.encode(login.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new AppException("User Role not set.")));
		roles.add(roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new AppException("User Role not set.")));
		login.setRoles(roles);
		loginRepository.save(login);

		User user = new User();
		user.setId(login.getUserId());
		user.setName("Ilias");
		user.setSurname("Mentzelos");
		user.setUsername("iliasmentz");
		String bday =  "22/08/1996";
		user.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(bday));
		userRepository.save(user);

	}
}