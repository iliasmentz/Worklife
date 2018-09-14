package com.linkedin.service;

import com.linkedin.converter.SkillConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.Skill;
import com.linkedin.entities.database.repo.SkillRepository;
import com.linkedin.entities.model.Skills.SkillDto;
import com.linkedin.entities.model.Skills.SkillRequestDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

  private SkillRepository skillRepository;
  private SkillConverter skillConverter;
  @Autowired
  public SkillService(SkillRepository skillRepository, SkillConverter skillConverter){

	this.skillRepository = skillRepository;
	this.skillConverter = skillConverter;
  }

  public List<SkillDto> getSkills() {
	Login login = AuthenticationFacade.authenticatedUser();

	return skillRepository.findByUserId(login.getUserId())
		.stream()
		.map(skillConverter::toSkillDto)
		.collect(Collectors.toList());
  }

  public List<SkillDto> getSkills(Long userId) {

	return skillRepository.findByUserId(userId)
		.stream()
		.map(skillConverter::toSkillDto)
		.collect(Collectors.toList());
  }

  public SkillDto createSkill(SkillRequestDto skillRequestDto) {
	Login login = AuthenticationFacade.authenticatedUser();
	Skill skill = new Skill();
	skill.setUserId(login.getUserId());
	skill.setLevel(skillRequestDto.getLevel());
	skill.setName(skillRequestDto.getName());
	skill.setVisible(skillRequestDto.getVisible());
	skillRepository.save(skill);
	return skillConverter.toSkillDto(skill);

  }

  public void deleteSkill(Long skillId) throws Exception {
    if(!skillRepository.existsById(skillId) ){
	  throw new ObjectNotFoundException(Skill.class, skillId);
	}

	Login login = AuthenticationFacade.authenticatedUser();
	Long userId = login.getUserId();
	Skill skillToDelete  = skillRepository.findById(skillId).orElse(null);
	if (userId != skillToDelete.getUserId()){
	  throw new NotAuthorizedException(Skill.class);

	}

	skillRepository.deleteById(skillId);

  }

  public SkillDto updateSkill(Long skillId, SkillRequestDto skillRequestDto) throws Exception {

	if(!skillRepository.existsById(skillId) ){
	  throw new ObjectNotFoundException(Skill.class, skillId);
	}
	Login login = AuthenticationFacade.authenticatedUser();
	Long userId = login.getUserId();

	//we check if the user is updating hist skills
	Skill skillToUpdate  = skillRepository.findById(skillId).orElse(null);
	if (userId != skillToUpdate.getUserId()){
	  throw new NotAuthorizedException(Skill.class);

	}

	skillToUpdate.setName(skillRequestDto.getName());
	skillToUpdate.setLevel(skillRequestDto.getLevel());
	skillToUpdate.setVisible(skillRequestDto.getVisible());

	skillRepository.save(skillToUpdate);
	return skillConverter.toSkillDto(skillToUpdate);
  }
}

