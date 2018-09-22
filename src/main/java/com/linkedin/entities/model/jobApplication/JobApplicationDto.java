package com.linkedin.entities.model.jobApplication;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "JobApplication Dto")
public class JobApplicationDto {
  @NotNull
  @ApiModelProperty(value = "JobApplicationId", example = "1", position = 1)
  private Long jobApplicationId;

  @NotNull
  @ApiModelProperty(value = "UserId the user that applied", example = "1", position = 2)
  private Long userId ;

  @NotNull
  @ApiModelProperty(value = "The jobId that the application refers to ", example = "1", position = 3)
  private Long jobId ;



}
