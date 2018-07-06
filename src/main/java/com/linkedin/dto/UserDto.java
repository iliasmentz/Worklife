package com.linkedin.dto;

import com.linkedin.database.User;
import lombok.Data;

@Data
public class UserDto extends BaseDto {
	private User user;
}
