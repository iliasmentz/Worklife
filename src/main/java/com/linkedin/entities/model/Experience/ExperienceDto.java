package com.linkedin.entities.model.Experience;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(description = "user's Experience Dto")
public class ExperienceDto {

  private Long experienceId;
  private String title;
  private String company;

  private Date statingDate;
  private Date endingDate;


}
