package com.linkedin.converter;

import com.linkedin.entities.database.Education;
import com.linkedin.entities.model.education.EducationDto;
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
