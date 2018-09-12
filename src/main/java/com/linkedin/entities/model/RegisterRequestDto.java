package com.linkedin.entities.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ApiModel(description = "register request object")
public class RegisterRequestDto {
	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "user name", example = "John", position = 1)
	private String name;

	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "user surname", example = "Doe", position = 2)
	private String surname;

	@NotBlank
	@Size(min = 3, max = 35)
	@ApiModelProperty(value = "username", example = "JohnDoe", position = 3)
	private String username;

	@NotBlank
	@Size(min = 3, max = 256)
	@Email
	@ApiModelProperty(value = "user email", example = "johndoe@gmail.com", position = 4)
	private String email;

	@NotBlank
	@Size(min = 6, max = 20)
	@ApiModelProperty(value = "user password", example = "123456", position = 5)
	private String password;

	@NotNull
	@ApiModelProperty(value = "user password", example = "123456", position = 6)
	private String password2;

//	@NotNull
	@ApiModelProperty(value = "user birthday (yyyy-mm-dd)", example = "1996-08-22", position = 7)
	private Date birthdate;

	@ApiModelProperty(value = "user address", example = "Wall Street 21", position = 8)
	private String address;

	@ApiModelProperty(value = "user phone", example = "+306971234567", position = 9)
	private String phoneNumber;

	@ApiModelProperty(value = "user image path", example = "/photos/test", position = 10)
	private String imgPath;
}
