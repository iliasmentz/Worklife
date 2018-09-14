package com.linkedin.entities.model.Skills;


import com.linkedin.constants.SkillLevel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "SkillRequestDto object ")
public class SkillRequestDto {

  @ApiModelProperty(value = "user_id", example = "1", position = 1)
  private Long userId;

  @ApiModelProperty(value = "name", example = "Data Mining", position =2)
  private String name;

  @ApiModelProperty(value = "level", example = "Senior", position =3)
  private SkillLevel level;

}
