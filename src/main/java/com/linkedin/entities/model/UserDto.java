package com.linkedin.entities.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserDto {
	@NotNull
	@ApiModelProperty(value = "userId", example = "10", position = 1)

	private Long userId;

	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "user name", example = "John", position = 1)
	private String name;

	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "display name", example = "John Doe", position = 1)
	private String displayName;

	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "display name", example = "John_Doe123", position = 1)
	private String username;

	@NotBlank
	@Size(min = 1, max = 40)
	@ApiModelProperty(value = "user surname", example = "Doe", position = 2)
	private String surname;

	@NotBlank
	@Size(min = 3, max = 254)
	@Email
	@ApiModelProperty(value = "user email", example = "johndoe@gmail.com", position = 2)
	private String email;

	@NotNull
	@ApiModelProperty(value = "user birthday (yyyy-mm-dd)", example = "1996-08-22", position = 7)
	private Date birthdate;
	@NotBlank
	@ApiModelProperty(value = "user address", example = "Wall Street 21", position = 8)
	private String address;
	@NotBlank
	@ApiModelProperty(value = "user phone", example = "+306971234567", position = 9)
	private String phoneNumber;
	@NotBlank
	@ApiModelProperty(value = "user image path", example = "/photos/test", position = 10)
	private String imagePath;
	@NotNull
	@ApiModelProperty(value = "user dateCreated(yyyy-mm-dd)", example = "1996-08-22", position = 7)
	private Date dateCreated;


}
