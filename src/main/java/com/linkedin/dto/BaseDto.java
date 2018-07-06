package com.linkedin.dto;

import com.linkedin.entities.RequestResponse;
import lombok.Data;

@Data
public class BaseDto {
	private RequestResponse requestResponse = new RequestResponse();
}
