package com.linkedin.model.skills;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel(description = "SkillDto object ")
public class SkillDto {

  @NotNull
  @ApiModelProperty(value = "skill_Id", example = "1", position = 1)
  private Long skillId;

  @NotBlank
  @ApiModelProperty(value = "name", example = "Data Mining", position = 3)
  private String name;

  @NotNull
  @ApiModelProperty(value = "level", example = "1", position = 4)
  private Integer level;

  @NotNull
  @ApiModelProperty(value = "visible", example = "Public", position = 5)
  private Integer visible;
}
