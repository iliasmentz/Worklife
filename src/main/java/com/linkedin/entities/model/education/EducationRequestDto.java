package com.linkedin.entities.model.education;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "education Request object")
public class EducationRequestDto {

	@ApiModelProperty(value = "university degree", example = "Bachelor", position = 1)
	private String universityDegree;

	@ApiModelProperty(value = "university name", example = "Kapodistriako", position = 2)
	private String universityName;

	@ApiModelProperty(value = "starting date", example = "2018-01-22", position = 3)
	private Date startingDate;

	@ApiModelProperty(value = "ending date", example = "2020-01-22", position = 4)
	private Date endingDate;
}
