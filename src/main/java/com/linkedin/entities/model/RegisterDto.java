package com.linkedin.entities.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "register request object")
public class RegisterDto {
	@ApiModelProperty(value = "status", example = "success", position = 1)
	private String status;

	public RegisterDto(String status) {
		this.status = status;
	}
}
