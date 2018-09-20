package com.linkedin.entities.model.connection;

import com.linkedin.entities.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "ConnectionRequestDto  response object ")
public class ConnectionRequestDto {
  @NotNull
  @ApiModelProperty(value = "connectionId", example = "11", position = 1)
  private Long connectionId;

  @NotNull
  @ApiModelProperty(value = "userRequested", example = "1", position = 2)
  private Long userRequestedId;

  @NotNull
  @ApiModelProperty(value = "userTarget", example = "2", position = 3)
  private Long userTargetId;

  @NotNull
  @ApiModelProperty(value = "dateOfAccepted", example = "2018-01-12", position = 4)
  private Date dateOfRequest;

  @ApiModelProperty(value = "connectionRequestId", example = "1", position = 5)
  private Long connectionRequestId;

  @ApiModelProperty(value = "status", example = "1", position = 6)
  private Long status; //0->pending , 1 -> accepted , 2 ->rejected
}

