package com.linkedin.entities;

import lombok.Data;

@Data
public class RequestResponse {
	public static final Integer SUCCESS_STATUS = 200;
	public static final Integer BAD_REQUEST_STATUS = 400;
	public static final Integer UNAUTHORIZED_STATUS = 401;

	private Integer status;
	private String message;

	public RequestResponse() {
		this.status = SUCCESS_STATUS;
		this.message = "Valid Request";
	}
}
