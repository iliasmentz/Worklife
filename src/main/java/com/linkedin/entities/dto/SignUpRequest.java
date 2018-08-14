package com.linkedin.entities.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SignUpRequest {
	@NotBlank
	@Size(min = 4, max = 40)
	private String name;

	@NotBlank
	@Size(min = 4, max = 40)
	private String surname;

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;

	@NotBlank
	@Size(max = 40)
	@Email
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	private String password;

	@NotNull
	private Date birthdate;

	@NotNull
	private String password2;

	private String address;
	private String phoneNumber;
	private String imgPath;
}
