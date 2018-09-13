package com.linkedin.converter;

import com.linkedin.entities.database.Experience;
import com.linkedin.entities.model.Experience.ExperienceDto;
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
		return experienceDto;
	}
}
