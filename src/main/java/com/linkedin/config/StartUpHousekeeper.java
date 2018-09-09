package com.linkedin.config;

import com.linkedin.constants.Role;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@Component
public class StartUpHousekeeper {
	private final static String[] RANDOM_USERS = new String[] {"thanasis", "frinh", "marinos", "apo", "kitsos", "mhtsos", "ceo", "proedros"};
	private final UserRepository userRepository;
	private final LoginRepository loginRepository;
	private final PasswordEncoder passwordEncoder;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String mode;

	@Autowired
	public StartUpHousekeeper(UserRepository userRepository, LoginRepository loginRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.loginRepository = loginRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() throws ParseException {
		if ("create".equals(mode)) {
			createAdmin();
			createSomeUsers();
		}
	}

	private void createSomeUsers() throws ParseException {
		for (String randomUserName : RANDOM_USERS) {
			String bday = new Random().nextInt(30) + "/08/1996";
			createRandomUserAndLogin(randomUserName, "123456", bday);
		}

		
	}

	private void createRandomUserAndLogin(String randomUserName, String password, String bday) throws ParseException {
		Login login = new Login(randomUserName, password);
		login.setPassword(passwordEncoder.encode(password));
		login.setRole(Role.ROLE_USER);
		loginRepository.save(login);

		User user = new User();
		user.setUsername(randomUserName);
		user.setId(login.getUserId());
		user.setName(randomUserName.toUpperCase());
		user.setSurname(randomUserName.toUpperCase().concat("Surname"));
		user.setUsername(randomUserName);
		user.setEmail(randomUserName + "@gmaiil.com");
		user.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(bday));
		userRepository.save(user);
	}

	private void createAdmin() throws ParseException {

		Login login = new Login("iliasmentz", "123456");
		login.setPassword(passwordEncoder.encode(login.getPassword()));
		login.setRole(Role.ROLE_ADMIN);
		loginRepository.save(login);

		User user = new User();
		user.setId(login.getUserId());
		user.setName("Ilias");
		user.setSurname("Mentzelos");
		user.setUsername("iliasmentz");
		user.setEmail("admin@admin.com");
		String bday =  "22/08/1996";
		user.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(bday));
		userRepository.save(user);

	}
}