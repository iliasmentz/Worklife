package com.linkedin.entities.model;

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

	public UserDto(User user) {
		this.email = user.getEmail();
		this.userId = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.birthdate = user.getBirthdate();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.imgPath = user.getImgPath();
	}
}
