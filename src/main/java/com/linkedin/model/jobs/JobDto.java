package com.linkedin.model.jobs;

import com.linkedin.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(description = "job response object")
public class JobDto {

  @NotNull
  @ApiModelProperty(value = "job id", example = "1234", position = 1)
  private Long id;
  @NotBlank
  @ApiModelProperty(value = "job title", example = "Software Engineer", position = 2)
  private String title;
  @NotNull
  @ApiModelProperty(value = "job's author", position = 3)
  private UserSimpleDto author;
  @NotBlank
  @ApiModelProperty(value = "company name", example = "Google Inc.", position = 4)
  private String company;
  @NotNull
  @ApiModelProperty(value = "job's creation date", example = "2018-01-22", position = 5)
  private Date dateCreated;
  @NotBlank
  @ApiModelProperty(value = "job's description", example = "Work in the best company in the world", position = 6)
  private String description;

  @ElementCollection
  @ApiModelProperty(value = "job's skills list", example = "['Python','C++','MySQL']", position = 7)
  private List<String> skills;

}
