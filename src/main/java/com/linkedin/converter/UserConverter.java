package com.linkedin.converter;

import com.linkedin.entities.database.Experience;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.ExperienceRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.service.ExperienceService;
import com.linkedin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

	private final UserRepository userRepository;
	private final ExperienceService experienceService;
	private final ExperienceRepository experienceRepository;
	@Autowired
	public UserConverter(UserRepository userRepository, ExperienceService experienceService, ExperienceRepository experienceRepository) {
		this.userRepository = userRepository;
	  this.experienceService = experienceService;
	  this.experienceRepository = experienceRepository;
	}


	public UserDto toUserDto(User user, Integer role) {
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
		userDto.setImagePath(getUserPhotoFullUrl(user));
		userDto.setDateCreated(user.getDateCreated());
		userDto.setRole(role);
		return userDto;
	}


	public UserSimpleDto toUserSimpleDto(Long userId) {

		User user = userRepository.findById(userId).orElse(null);
		UserSimpleDto userDto = new UserSimpleDto();
		userDto.setDisplayName(user.getName() + ' ' + user.getSurname());
		userDto.setUserId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setImagePath(getUserPhotoFullUrl(user));

		//experienceService.getUsersExperiences(userId).sort();
		Experience experience = experienceRepository.findAllByUserIdOrderByStartDateDesc(userId).stream().findFirst().orElse(null);
		if(experience != null ){
		  userDto.setJobTitleCompany(experience.getTitle() + " at " +experience.getCompany()  );
		}


		return userDto;
	}

	public UserSimpleDto toUserSimpleDto(User user) {
		UserSimpleDto userDto = new UserSimpleDto();
		userDto.setDisplayName(user.getName() + ' ' + user.getSurname());
		userDto.setUserId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setImagePath(getUserPhotoFullUrl(user));

		return userDto;
	}

	/*
	 * Return the full path of users photo.
	 * If the photo doesn't exists returns empty String
	 */
	public String getUserPhotoFullUrl(User user) {
		if (user.getImgPath() != null) {
			return FileService.getFileFullUrl(user.getImgPath());
		} else {
			return "";
		}
	}

}
