package com.linkedin.entities.dto;

import com.linkedin.entities.RequestResponse;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
	private RequestResponse requestResponse = new RequestResponse();
}
