package com.linkedin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "user's simple object")
public class UserSimpleDto implements Serializable {

  @ApiModelProperty(value = "user's id", example = "1234", position = 1)
  private Long userId;

  @ApiModelProperty(value = "user's username", example = "johnDoe", position = 2)
  private String username;

  @ApiModelProperty(value = "user's display name", example = "John Doe", position = 3)
  private String displayName;

  @ApiModelProperty(value = "user's image path", example = "", position = 4)
  private String imagePath;

  @ApiModelProperty(value = "JobTitleCompany ", example = "Software Engineer", position = 5)
  private String jobTitleCompany;

}
