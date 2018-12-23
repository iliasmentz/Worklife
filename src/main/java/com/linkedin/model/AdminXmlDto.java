package com.linkedin.model;

import com.linkedin.model.comment.CommentDto;
import com.linkedin.model.connection.ConnectionDto;
import com.linkedin.model.experience.ExperienceDto;
import com.linkedin.model.jobs.JobDto;
import com.linkedin.model.post.PostDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "Admin xml response")
public class AdminXmlDto implements Serializable {
  @ApiModelProperty(value = "userId", example = "1", position = 1)
  private Long userId;

  @ApiModelProperty(value = "userDto", example = "UserDto", position = 2)
  private UserDto userDto;

  @ElementCollection
  @ApiModelProperty(value = "list of PostDto", example = "[PostDto1, PostDto2]", position = 3)
  private List<PostDto> posts;

  @ElementCollection
  @ApiModelProperty(value = "list of PostDto", example = "[ExperienceDto1, ExperienceDto2]", position = 4)
  private List<ExperienceDto> experiences;

  @ElementCollection
  @ApiModelProperty(value = "list of JobDto of jobs he created", example = "[JobDto1, JobDto2]", position = 5)
  private List<JobDto> jobs;

  @ElementCollection
  @ApiModelProperty(value = "list of CommentDto", example = "[CommentDto1, CommentDto2]", position = 6)
  private List<CommentDto> comments;


  @ElementCollection
  @ApiModelProperty(value = "list of LikeDto", example = "[LikeDto1, LikeDto2]", position = 7)
  private List<LikeDto> likes;

  @ElementCollection
  @ApiModelProperty(value = "list of ConnectionDto", example = "[ConnectionDto1, ConnectionDto2]", position = 8)
  private List<ConnectionDto> connectionDtoList;
}
