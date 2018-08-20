package com.linkedin.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ApiResponse implements Serializable {
	private Boolean success;
	private String message;

	public ApiResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
