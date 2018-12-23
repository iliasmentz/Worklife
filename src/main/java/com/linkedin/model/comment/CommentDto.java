package com.linkedin.model.comment;

import com.linkedin.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "CommentDto response object ")
public class CommentDto {
  @NotNull
  @ApiModelProperty(value = "commentId", example = "11", position = 1)
  private Long commentId;


  @NotNull
  @ApiModelProperty(value = "postId", example = "11", position = 2)
  private Long postId;

  @NotNull
  @ApiModelProperty(value = "commenter", position = 3)
  private UserSimpleDto commenter;


  @NotNull
  @ApiModelProperty(value = "commentDate (yyyy-mm-dd)", example = "2018-01-12", position = 4)
  private Date commentDate;

  @NotBlank
  @ApiModelProperty(value = "context", example = "This Post is the greatest thing ever ", position = 5)
  private String context;


}
