package com.linkedin.entities.model.Likes;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@ApiModel(description = "LikeDto object ")
public class LikeDto {

  @NotNull
  @ApiModelProperty(value = "likeId", example = "11", position = 1)
  private Long likeId;

  @NotNull
  @ApiModelProperty(value = "userId", example = "11", position = 2)
  private Long userId;

  @NotNull
  @ApiModelProperty(value = "postId", example = "11", position = 3)
  private Long postId;
}
