package com.linkedin.entities.model.Skills;


import com.linkedin.constants.SkillLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "SkillDto object ")
public class SkillDto {

  @ApiModelProperty(value = "skill_Id", example = "1", position = 1)
  private Long skillId;

  @ApiModelProperty(value = "user_id", example = "1", position = 2)
  private Long userId;

  @ApiModelProperty(value = "name", example = "Data Mining", position =3)
  private String name;

  @ApiModelProperty(value = "level", example = "Senior", position =4)
  private SkillLevel level;

}
