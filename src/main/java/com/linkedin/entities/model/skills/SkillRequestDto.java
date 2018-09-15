package com.linkedin.entities.model.skills;

import com.linkedin.constants.Visible;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel(description = "SkillRequestDto object ")
public class SkillRequestDto {

	@NotBlank
	@ApiModelProperty(value = "name", example = "Data Mining", position = 2)
	private String name;

	@NotNull
	@ApiModelProperty(value = "level", example = "1", position = 3)
	private Integer level;

	@NotNull
	@ApiModelProperty(value = "visible", example = "Public", position = 5)
	private Visible visible;

}
