package com.linkedin.model.post;

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
@ApiModel(description = "PostDto object ")
public class PostDto {

  @NotNull
  @ApiModelProperty(value = "postId", example = "11", position = 1)
  private Long postId;

  @NotNull
  @ApiModelProperty(value = "commenter", position = 2)
  private UserSimpleDto creator;

  @NotNull
  @ApiModelProperty(value = "postDate (yyyy-mm-dd)", example = "2018-01-12", position = 3)
  private Date postDate;

  @NotBlank
  @ApiModelProperty(value = "context", example = "Check out our Brand new offices", position = 4)
  private String context;

  @NotBlank
  @ApiModelProperty(value = "visible", example = "Public", position = 5)
  private Integer visible;

  @NotNull
  @ApiModelProperty(value = "numberOfLikes", example = "12", position = 6)
  private Long numberOfLikes;

  @ApiModelProperty(value = "filePath", example = "https://localhost:8080/downloadUrl/post-5556", position = 7)
  private String filePath;

  @ApiModelProperty(value = "image type", example = "", position = 8)
  private String type;

}

