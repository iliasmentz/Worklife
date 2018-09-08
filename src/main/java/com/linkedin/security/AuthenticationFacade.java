package com.linkedin.security;

import com.linkedin.entities.database.Login;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AuthenticationFacade {

	private static Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public static Login authenticatedUser() {
		MyUserDetails userDetails = (MyUserDetails) getAuthentication().getPrincipal();
		return userDetails.getLogin();
	}
	public static Long getUserId() {
		return 1L; //TODO remove this because user is always 1
		//return  authenticatedUser().getUserId();

	}


}
