package com.linkedin.converter;

import com.linkedin.entities.database.Connection;
import com.linkedin.entities.database.ConnectionRequest;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.entities.model.connection.ConnectionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConnectionConverter {

	private final UserConverter userConverter;

	@Autowired
	public ConnectionConverter(UserConverter userConverter) {
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


	public ConnectionRequestDto toConnectionRequestDto(ConnectionRequest connectionRequest) {
		ConnectionRequestDto connectionRequestDto = new ConnectionRequestDto();
		connectionRequestDto.setDateOfRequest(connectionRequest.getDateOfRequest());
		connectionRequestDto.setStatus(connectionRequest.getStatus());
		connectionRequestDto.setUserRequested(userConverter.toUserSimpleDto(connectionRequest.getUserRequestedId()));
		connectionRequestDto.setUserTargetId(connectionRequest.getUserTargetId());
		connectionRequestDto.setConnectionRequestId(connectionRequest.getConnectionRequestId());

		return connectionRequestDto;

	}
}
