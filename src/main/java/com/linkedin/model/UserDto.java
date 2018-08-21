package com.linkedin.model;

import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
	private String email;
	private Long userId;
	private String name;
	private String username;
	private Date birthdate;
	private String address;
	private String phoneNumber;
	private String imgPath;

	public UserDto(Login login, User user) {
		this.email = login.getEmail();
		this.userId = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.birthdate = user.getBirthdate();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.imgPath = user.getImgPath();
	}

	public UserDto(User user) {
		this.userId = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.birthdate = user.getBirthdate();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.imgPath = user.getImgPath();
	}
}
