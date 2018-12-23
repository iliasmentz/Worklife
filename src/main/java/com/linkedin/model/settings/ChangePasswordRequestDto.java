package com.linkedin.model.settings;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "Change Password request object ")
public class ChangePasswordRequestDto {
  @ApiModelProperty(value = "oldPassword", example = "123456", position = 1)
  private String oldPassword;

  @ApiModelProperty(value = "newPassword", example = "11", position = 2)
  private String newPassword;

  @ApiModelProperty(value = "newPasswordRepeat", example = "11", position = 3)
  private String newPasswordRepeat;


}
