package com.linkedin.model.experience;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@ApiModel(description = "user's ExperienceRequestDto")
public class ExperienceRequestDto {

  @NotBlank
  @ApiModelProperty(value = "Title ", example = "Software Engineer", position = 1)
  private String title;

  @NotBlank
  @ApiModelProperty(value = "Company Name", example = "Google", position = 2)
  private String company;

  @NotNull
  @ApiModelProperty(value = "Date started", example = "2010-01-01", position = 3)
  private Date startDate;

  @ApiModelProperty(value = "Date ended", example = "2018-01-01", position = 4)
  private Date endDate;

  @NotNull
  private Integer visible;
}
