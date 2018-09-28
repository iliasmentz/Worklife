package com.linkedin.entities.model.changePasswordEmail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@ApiModel(description = "Change Email request object ")
public class ChangeEmailRequestDto {

  @ApiModelProperty(value = "newEmail", example = "kainourgionEmail@.com", position = 1)
  private String newEmail;

}
