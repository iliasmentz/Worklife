package com.linkedin.converter;

import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

	private final UserRepository userRepository;

	@Autowired
	public UserConverter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setEmail(user.getEmail());
		userDto.setUserId(user.getId());
		userDto.setName(user.getName());
		userDto.setSurname(user.getSurname());
		userDto.setDisplayName(user.getName() + ' ' + user.getSurname());
		userDto.setUsername(user.getUsername());
		userDto.setBirthdate(user.getBirthdate());
		userDto.setAddress(user.getAddress());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setImagePath(FileService.getFileFullUrl(user.getImgPath()));
		userDto.setDateCreated(user.getDateCreated());
		return userDto;
	}


	public UserSimpleDto toUserSimpleDto(Long id) {
		User user = userRepository.getOne(id);
		UserSimpleDto userDto = new UserSimpleDto();
		userDto.setDisplayName(user.getName() + ' ' + user.getSurname());
		userDto.setUserId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setImagePath(FileService.getFileFullUrl(user.getImgPath()));
		return userDto;
	}

}
