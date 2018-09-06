package com.linkedin.entities.model.jobs;

import com.linkedin.entities.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "job response object")
public class JobDto {

	@ApiModelProperty(value = "job id", example = "1234", position = 1)
	private Long id;

	@ApiModelProperty(value = "job title", example = "Software Engineer", position = 2)
	private String title;

	@ApiModelProperty(value = "job's author", position = 3)
	private UserSimpleDto author;

	@ApiModelProperty(value = "company name", example = "Google Inc.", position = 4)
	private String company;

	@ApiModelProperty(value = "job's creation date", example = "2018-01-22", position = 5)
	private Date dateCreated;

	@ApiModelProperty(value = "job's description", example = "Work in the best company in the world", position = 6)
	private String description;

	//TODO : add skills

}
