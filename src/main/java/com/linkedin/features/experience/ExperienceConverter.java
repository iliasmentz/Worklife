package com.linkedin.features.experience;

import com.linkedin.entities.Experience;
import com.linkedin.model.experience.ExperienceDto;
import org.springframework.stereotype.Component;

@Component
public class ExperienceConverter {
  public ExperienceDto toExperienceDto(Experience experience) {
    ExperienceDto experienceDto = new ExperienceDto();

    experienceDto.setCompany(experience.getCompany());
    experienceDto.setTitle(experience.getTitle());
    experienceDto.setStartDate(experience.getStartDate());
    experienceDto.setEndDate(experience.getEndDate());
    experienceDto.setExperienceId(experience.getExperienceId());
    experienceDto.setVisible(experience.getVisible());
    experienceDto.setUserId(experience.getUserId());
    return experienceDto;
  }
}
