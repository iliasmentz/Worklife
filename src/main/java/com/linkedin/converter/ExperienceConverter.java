package com.linkedin.converter;

import com.linkedin.controller.ExperienceController;
import com.linkedin.entities.database.Experience;
import com.linkedin.entities.model.Experience.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceConverter {

	@Autowired
  	public void  ExperienceController(){

	}

	public ExperienceDto toExperienceDto(Experience experience){
	  	ExperienceDto experienceDto = new ExperienceDto();

		experienceDto.setCompany(experience.getCompany());
	  	experienceDto.setTitle(experience.getTitle());
	  	experienceDto.setStatingDate(experience.getStartingDate());
	  	experienceDto.setEndingDate(experience.getEndingDate());
	  	experienceDto.setExperienceId(experience.getExperienceId());
	  	return experienceDto;
	}
}
