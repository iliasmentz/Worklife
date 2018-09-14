package com.linkedin.converter;

import com.linkedin.entities.database.Skill;
import com.linkedin.entities.database.repo.SkillRepository;
import com.linkedin.entities.model.Skills.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class SkillConverter {

  private SkillRepository skillRepository;

  @Autowired
  public SkillConverter(SkillRepository skillRepository){
	this.skillRepository = skillRepository;
  }

	public SkillDto toSkillDto(Skill skill){
    	SkillDto skillDto = new SkillDto();
    	skillDto.setSkillId(skill.getSkillId());
    	skillDto.setUserId(skill.getUserId());
    	skillDto.setLevel(skill.getLevel());
    	skillDto.setName(skill.getName());
		skillDto.setVisible(skill.getVisible());
    	return skillDto;
	}
}
