package com.linkedin.controller;


import com.linkedin.converter.SkillConverter;
import com.linkedin.entities.database.Skill;
import com.linkedin.entities.database.repo.SkillRepository;
import com.linkedin.entities.model.Skills.SkillDto;
import com.linkedin.entities.model.Skills.SkillRequestDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.service.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = SkillController.tag)
@RestController
@RequestMapping("/api/profile/skills/")
public class SkillController {

  public static final String tag = "Skills Controller";

  private SkillRepository skillRepository;
  private SkillConverter skillConverter;
  private SkillService skillService;
  @Autowired
  public SkillController(SkillRepository skillRepository, SkillConverter skillConverter, SkillService skillService) {

	this.skillRepository = skillRepository;
	this.skillConverter = skillConverter;
	this.skillService = skillService;
  }
  @GetMapping("/")
  @ApiOperation(value = "Skill", notes = "Returns User's List of Skills", response = Skill.class)
  public List<SkillDto> getSkills() {
    return skillService.getSkills();
  }

  @GetMapping("/{userId}")
  @ApiOperation(value = "Skill", notes = "Returns anothers User's List of Skills", response = Skill.class)
  public List<SkillDto> getSkills(@PathVariable Long userId) {
	return skillService.getSkills(userId);
  }
  @PostMapping("/")
  @ApiOperation(value = "Skill", notes = "Creates a new skill for the User",response = Skill.class)
  public SkillDto createSkill(@Valid @RequestBody SkillRequestDto skillRequestDto) {

    return skillService.createSkill(skillRequestDto);

  }

  @PutMapping("/{skillId}")
  @ApiOperation(value = "Skill", notes = "Updates a skill of the User",response = Skill.class)
  public SkillDto deleteSkill(@PathVariable Long skillId , @Valid @RequestBody SkillRequestDto skillRequestDto) throws  Exception{

	return skillService.updateSkill(skillId, skillRequestDto);

  }

  @DeleteMapping("/{skillId}")
  @ApiOperation(value = "Skill", notes = "Deletes a skill of the User",response = Skill.class)
  public void deleteSkill(@PathVariable Long skillId) throws  Exception{

	skillService.deleteSkill(skillId);

  }
}
