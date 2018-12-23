package com.linkedin.features.education;

import com.linkedin.entities.Education;
import com.linkedin.model.education.EducationDto;
import org.springframework.stereotype.Component;

@Component
public class EducationConverter {

  public EducationDto toEducationDto(Education education) {
    EducationDto educationDto = new EducationDto();
    educationDto.setEducationId(education.getEducationId());
    educationDto.setEndDate(education.getEndDate());
    educationDto.setUniversityDegree(education.getUniversityDegree());
    educationDto.setUniversityName(education.getUniversityName());
    educationDto.setStartDate(education.getStartDate());
    educationDto.setVisible(education.getVisible());
    return educationDto;
  }

}
