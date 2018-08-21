package com.linkedin.security;

import com.linkedin.entities.database.Login;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Login authenticatedUser() {
		MyUserDetails userDetails = (MyUserDetails) getAuthentication().getPrincipal();
		return userDetails.getUser();
	}


}
