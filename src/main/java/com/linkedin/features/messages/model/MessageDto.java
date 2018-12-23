package com.linkedin.features.messages.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

  @ApiModelProperty(value = "message", example = "Hello", position = 0)
  private String message;

  @ApiModelProperty(value = "0 -> me, 1 -> user", example = "1", position = 1)
  private Integer sendBy;

  @ApiModelProperty(value = "sendDate", example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", position = 3)
  private Date sendDate;
}
