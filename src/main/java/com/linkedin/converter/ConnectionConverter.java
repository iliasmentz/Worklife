package com.linkedin.converter;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.entities.model.jobs.JobDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionConverter {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Autowired
  public ConnectionConverter(UserRepository userRepository, UserConverter userConverter) {
	this.userRepository = userRepository;
	this.userConverter = userConverter;
  }

  public ConnectionDto toConnectionDto(Connection connection) {
	ConnectionDto connectionDto = new ConnectionDto();
	connectionDto.setConnectionId(connection.getConnectionId());
	connectionDto.setConnectionRequestId(connection.getConnectionRequestId());
	connectionDto.setCreateDate(connection.getCreateDate());
	connectionDto.setUserAccepted(userConverter.toUserSimpleDto(connection.getUserAcceptedId()));
	connectionDto.setUserRequested(userConverter.toUserSimpleDto(connection.getUserRequestedId()));

	return connectionDto;

  }


  public JobDto toJobDto(Job job) {
	JobDto dto = new JobDto();
	dto.setId(job.getJobId());
	dto.setTitle(job.getTitle());
	dto.setCompany(job.getCompany());
	dto.setAuthor(userConverter.toUserSimpleDto(job.getAuthorId()));
	dto.setDescription(job.getDescription());
	dto.setDateCreated(job.getDate());
	return dto;
  }


}
