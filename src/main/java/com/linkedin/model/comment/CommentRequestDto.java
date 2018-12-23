package com.linkedin.model.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@ApiModel(description = "CommentDto Request object ")
public class CommentRequestDto {

  @ApiModelProperty(value = "postId", example = "10", position = 1)
  private Long postId;

  @NotBlank
  @ApiModelProperty(value = "context", example = "This Post is the greatest thing ever ", position = 4)
  private String context;
}
