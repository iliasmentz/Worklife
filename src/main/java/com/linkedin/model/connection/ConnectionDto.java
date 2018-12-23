package com.linkedin.model.connection;

import com.linkedin.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "ConnectionDto  response object ")
public class ConnectionDto {
  @NotNull
  @ApiModelProperty(value = "connectionId", example = "11", position = 1)
  private Long connectionId;

  @NotNull
  @ApiModelProperty(value = "userRequested", example = "{userid, username,displayname, imgPath}", position = 2)
  private UserSimpleDto userRequested;

  @NotNull
  @ApiModelProperty(value = "userAccepted", example = "{userid, username,displayname, imgPath}", position = 3)
  private UserSimpleDto userAccepted;

  @NotNull
  @ApiModelProperty(value = "create_date", example = "2018-01-12", position = 4)
  private Date createDate;

  @ApiModelProperty(value = "connectionRequestId", example = "1", position = 4)
  private Long connectionRequestId;

}
