package com.linkedin.controller;

import com.linkedin.entities.model.skills.SkillDto;
import com.linkedin.entities.model.skills.SkillRequestDto;
import com.linkedin.service.SkillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = SkillController.tag)
@RestController
@RequestMapping("/api/profile/skills/")
public class SkillController {

	public static final String tag = "Skills Controller";

	private SkillService skillService;

	@Autowired
	public SkillController(SkillService skillService) {
		this.skillService = skillService;
	}

	@GetMapping("/")
	@ApiOperation(value = "Skill", notes = "Returns User's List of Skills", response = SkillDto.class)
	public List<SkillDto> getSkills() {
		return skillService.getSkills();
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "Skill", notes = "Returns another's user List of Skills", response = SkillDto.class)
	public List<SkillDto> getSkills(@PathVariable Long userId) {
		return skillService.getSkills(userId);
	}

	@PostMapping("/")
	@ApiOperation(value = "Skill", notes = "Creates a new skill for the User", response = SkillDto.class)
	public SkillDto createSkill(@Valid @RequestBody SkillRequestDto skillRequestDto) {

		return skillService.createSkill(skillRequestDto);

	}

	@PutMapping("/{skillId}")
	@ApiOperation(value = "Skill", notes = "Updates a skill of the User", response = SkillDto.class)
	public SkillDto updateSkill(@PathVariable Long skillId, @Valid @RequestBody SkillRequestDto skillRequestDto) throws Exception {

		return skillService.updateSkill(skillId, skillRequestDto);

	}

	@DeleteMapping("/{skillId}")
	@ApiOperation(value = "Skill", notes = "Deletes a skill of the User")
	public void deleteSkill(@PathVariable Long skillId) throws Exception {

		skillService.deleteSkill(skillId);

	}
}
