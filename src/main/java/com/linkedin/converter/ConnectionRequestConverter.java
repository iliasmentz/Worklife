package com.linkedin.converter;

import com.linkedin.entities.database.ConnectionRequest;
import com.linkedin.entities.model.connection.ConnectionRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ConnectionRequestConverter {


  public ConnectionRequestDto toConnectionRequestDto(ConnectionRequest connectionRequest) {
	ConnectionRequestDto connectionRequestDto = new ConnectionRequestDto();
	connectionRequestDto.setConnectionRequestId(connectionRequest.getConnectionRequestId());
	// connectionRequestDto.setConnectionId(connectionRequest.get);
	connectionRequestDto.setDateOfRequest(connectionRequest.getDateOfRequest());
	connectionRequestDto.setStatus(connectionRequest.getStatus());
	connectionRequestDto.setUserRequestedId(connectionRequest.getUserRequestedId());
	connectionRequestDto.setUserTargetId(connectionRequest.getUserTargetId());
	return connectionRequestDto;

  }
}
