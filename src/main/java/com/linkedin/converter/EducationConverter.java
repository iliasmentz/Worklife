package com.linkedin.converter;

import com.linkedin.entities.database.Education;
import com.linkedin.entities.model.UserDto;
import com.linkedin.entities.model.education.EducationDto;
import org.springframework.stereotype.Component;

@Component
public class EducationConverter {

    public EducationDto toEducationDTO(Education education){
       EducationDto educationDto = new EducationDto();
       educationDto.setId(education.getEducationId());
       educationDto.setEndingDate(education.getEndingDate());
       educationDto.setUniversityDegree(education.getUniversityDegree());
       educationDto.setUniversityName(education.getUniversity_name());
       educationDto.setStartingDate(education.getStartingDate());
       return educationDto;

    }

}
