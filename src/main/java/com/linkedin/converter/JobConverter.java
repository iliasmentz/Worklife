package com.linkedin.converter;

import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.entities.model.jobs.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobConverter {

	private final UserRepository userRepository;

	@Autowired
	public JobConverter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public JobDto toJobDto(Job job) {
		JobDto dto = new JobDto();
		dto.setId(job.getJobId());
		dto.setTitle(job.getTitle());
		dto.setCompany(job.getCompany());
		dto.setAuthor(toUserSimpleDto(job.getAuthorId()));
		dto.setDescription(job.getDescription());
		dto.setDateCreated(job.getDate());
		return dto;
	}

	public UserSimpleDto toUserSimpleDto(Long id) {
		User user = userRepository.getOne(id);
		UserSimpleDto userDto = new UserSimpleDto();
		userDto.setDisplayName(user.getName() + ' ' + user.getSurname());
		userDto.setUserId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setImagePath(user.getImgPath());
		return userDto;
	}

}
