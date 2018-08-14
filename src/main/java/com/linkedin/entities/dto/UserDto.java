package com.linkedin.entities.dto;

import com.linkedin.entities.database.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends BaseDto implements Serializable {
	private User user;

	private Long userId;
	private String name;
	private String username;
	private String email;
	private Date birthdate;
	private String address;
	private String phoneNumber;
	private String imgPath;

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
