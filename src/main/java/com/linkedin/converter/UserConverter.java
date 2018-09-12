package com.linkedin.converter;

import com.linkedin.entities.database.User;
import com.linkedin.entities.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

	public UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail() );
		userDto.setUserId(user.getId());
		userDto.setName(user.getName());
		userDto.setSurname(user.getSurname());
		userDto.setDisplayName(user.getName() +' '+user.getSurname());
		userDto.setUsername(user.getUsername() );
		userDto.setBirthdate(user.getBirthdate() );
		userDto.setAddress(user.getAddress() );
		userDto.setPhoneNumber(user.getPhoneNumber() );
		userDto.setImgPath(user.getImgPath() );
		userDto.setDateCreated(user.getDateCreated());
		return userDto;
	}




}
