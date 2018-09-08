package com.linkedin.errors;

import lombok.Data;

@Data
public class HttpError {
	private String message;
	private Integer code;

	public HttpError() {
	}

	public HttpError(String message, Integer code) {
		this.message = message;
		this.code = code;
	}
}