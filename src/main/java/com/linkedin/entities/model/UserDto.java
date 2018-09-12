package com.linkedin.entities.model;

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
	private String surname;

	private Date birthdate;
	private String displayName;
	private String address;
	private String phoneNumber;
	private String imgPath;
	private Date dateCreated;
}
