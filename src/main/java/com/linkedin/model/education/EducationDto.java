package com.linkedin.model.education;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "education Response object")
public class EducationDto {
  @NotNull
  @ApiModelProperty(value = "education id", example = "1234", position = 1)
  private Long educationId;

  @NotBlank
  @ApiModelProperty(value = "university degree", example = "Bachelor", position = 2)
  private String universityDegree;

  @NotBlank
  @ApiModelProperty(value = "university name", example = "Kapodistriako", position = 3)
  private String universityName;

  @NotNull
  @ApiModelProperty(value = "starting date", example = "2018-01-22", position = 5)
  private Date startDate;

  @ApiModelProperty(value = "ending date", example = "2018-01-22", position = 6)
  private Date endDate;

  @NotNull
  @ApiModelProperty(value = "Visible(if is public, private or friendsonly)", example = "Private", position = 7)
  private Integer visible;
}








