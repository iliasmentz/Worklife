package com.linkedin.entities.model.jobs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "job Request object")

public class JobRequestDto implements Serializable {
  @NotBlank
  @ApiModelProperty(value = "job title", example = "Software Engineer", position = 2)
  private String title;

  @NotBlank
  @ApiModelProperty(value = "company name", example = "Google Inc.", position = 4)
  private String company;

  @NotBlank
  @ApiModelProperty(value = "job's description", example = "Work in the best company in the world", position = 6)
  private String description;

  @ElementCollection
  @ApiModelProperty(value = "job's skills list", example = "['Python','C++','MySQL']", position = 7)
  public List<String> skills;


}
