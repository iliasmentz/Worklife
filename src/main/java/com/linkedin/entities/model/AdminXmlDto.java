package com.linkedin.entities.model;

import com.linkedin.entities.model.Comment.CommentDto;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.connection.ConnectionDto;
import com.linkedin.entities.model.experience.ExperienceDto;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.likes.LikeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.ConcurrentModificationException;
import java.util.List;

@Data
@ApiModel(description = "Admin xml response")

public class AdminXmlDto {
  @ApiModelProperty(value = "userId", example = "1", position = 1)
  private Long userId;

  @ApiModelProperty(value = "userDto", example = "UserDto", position = 2)
  private UserDto userDto;

  @ElementCollection
  @ApiModelProperty(value = "list of PostDto", example = "[PostDto1, PostDto2]", position = 3)
  public List<PostDto> posts;

  @ElementCollection
  @ApiModelProperty(value = "list of PostDto", example = "[ExperienceDto1, ExperienceDto2]", position = 4)
  public List<ExperienceDto> experiences;

  @ElementCollection
  @ApiModelProperty(value = "list of JobDto of jobs he created", example = "[JobDto1, JobDto2]", position = 5)
  public List<JobDto> jobs;

  @ElementCollection
  @ApiModelProperty(value = "list of CommentDto", example = "[CommentDto1, CommentDto2]", position = 6)
  public List<CommentDto> comments;


  @ElementCollection
  @ApiModelProperty(value = "list of LikeDto", example = "[LikeDto1, LikeDto2]", position = 7)
  public List<LikeDto> likes;

  @ElementCollection
  @ApiModelProperty(value = "list of ConnectionDto", example = "[ConnectionDto1, ConnectionDto2]", position = 8)
  public List<ConnectionDto> connectionDtoList;
}
