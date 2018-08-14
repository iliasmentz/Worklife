package com.linkedin.payload;

import com.linkedin.entities.database.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoginDto implements Serializable {
	private String accessToken;
	private String tokenType = "Bearer";
	private Long userId;
	private String name;
	private String surname;
	private String username;

	public LoginDto(String accessToken, User user) {
		this.accessToken = accessToken;
		this.userId = user.getId();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.username = user.getUsername();
	}
}
