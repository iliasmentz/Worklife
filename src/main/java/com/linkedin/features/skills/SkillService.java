package com.linkedin.features.skills;

import com.linkedin.config.errors.NotAuthorizedException;
import com.linkedin.config.errors.ObjectNotFoundException;
import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Skill;
import com.linkedin.entities.repo.SkillRepository;
import com.linkedin.model.skills.SkillDto;
import com.linkedin.model.skills.SkillRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

  private SkillRepository skillRepository;
  private SkillConverter skillConverter;

  @Autowired
  public SkillService(SkillRepository skillRepository, SkillConverter skillConverter) {

    this.skillRepository = skillRepository;
    this.skillConverter = skillConverter;
  }

  public List<SkillDto> getSkills() {
    return skillRepository.findByUserId(AuthenticationFacade.getUserId())
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
    Skill skill = skillConverter.toSkill(AuthenticationFacade.getUserId(), skillRequestDto);
    skillRepository.save(skill);
    return skillConverter.toSkillDto(skill);
  }

  public void deleteSkill(Long skillId) throws Exception {
    if (!skillRepository.existsById(skillId)) {
      throw new ObjectNotFoundException(Skill.class, skillId);
    }

    Long userId = AuthenticationFacade.getUserId();
    Skill skillToDelete = skillRepository.findById(skillId).orElse(null);
    if (!userId.equals(skillToDelete != null ? skillToDelete.getUserId() : null)) {
      throw new NotAuthorizedException(Skill.class);
    }

    skillRepository.deleteById(skillId);
  }

  public SkillDto updateSkill(Long skillId, SkillRequestDto skillRequestDto) throws Exception {

    if (!skillRepository.existsById(skillId)) {
      throw new ObjectNotFoundException(Skill.class, skillId);
    }
    Long userId = AuthenticationFacade.getUserId();

    //we check if the user is updating hist skills
    Skill skillToUpdate = skillRepository.findById(skillId).orElse(null);
    if (!userId.equals(skillToUpdate != null ? skillToUpdate.getUserId() : null)) {
      throw new NotAuthorizedException(Skill.class);
    }

    skillToUpdate.setName(skillRequestDto.getName());
    skillToUpdate.setLevel(skillRequestDto.getLevel());
    skillToUpdate.setVisible(skillRequestDto.getVisible());

    skillRepository.save(skillToUpdate);
    return skillConverter.toSkillDto(skillToUpdate);
  }
}

