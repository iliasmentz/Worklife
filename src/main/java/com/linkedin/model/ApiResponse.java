package com.linkedin.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


@Data
public class ApiResponse implements Serializable {
	public static final HttpStatus SUCCESS_STATUS = HttpStatus.OK;
	public static final Integer BAD_REQUEST_STATUS = 400;
	public static final Integer UNAUTHORIZED_STATUS = 401;

	private HttpStatus success;
	private String message;

	public ApiResponse() {
		this.success = SUCCESS_STATUS;
	}


	public ApiResponse(String message) {
		this.success = SUCCESS_STATUS;
		this.message = message;
	}


	public ApiResponse(String message, HttpStatus status) {
		this.success = status;
		this.message = message;
	}
}
