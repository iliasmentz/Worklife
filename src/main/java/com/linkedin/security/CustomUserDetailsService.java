package com.linkedin.security;

import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final LoginRepository loginRepository;

	@Autowired
	public CustomUserDetailsService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		// Let people login with either username or email
		Login user = loginRepository.findByEmail(email)
				.orElseThrow(() ->
						new UsernameNotFoundException("User not found with email : " + email)
				);

		return UserPrincipal.create(user);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		Login user = loginRepository.findByUserId(id).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + id)
		);

		return UserPrincipal.create(user);
	}
}