package com.linkedin.entities.model.Post;

import com.linkedin.constants.Visible;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@ApiModel(description = "PostDto object ")
public class PostDto {

  @NotNull
  @ApiModelProperty(value = "postId", example = "11", position = 1)
  private Long postId;


  @NotNull
  @ApiModelProperty(value = "commenterId", example = "11", position = 2)
  private Long creatorId;


  @NotNull
  @ApiModelProperty(value = "postDate (yyyy-mm-dd)", example = "2018-01-12", position = 3)
  private Date postDate;

  @NotBlank
  @ApiModelProperty(value = "context", example = "Check out our Brand new offices", position = 4)
  private String context;

  @NotBlank
  @ApiModelProperty(value = "visible", example = "Public", position = 5)
  private Visible visible;
}

