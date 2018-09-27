package com.linkedin.entities.model.connection;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "ConnectionStatusDto  response ")
public class ConnectionStatusDto {
  @ApiModelProperty(value = "connectionStatus", example = "0", position = 1)
  private Integer connectionStatus;
}
