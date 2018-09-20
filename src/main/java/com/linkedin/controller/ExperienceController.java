package com.linkedin.controller;

import com.linkedin.entities.model.experience.ExperienceDto;
import com.linkedin.entities.model.experience.ExperienceRequestDto;
import com.linkedin.service.ExperienceService;
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

@Api(tags = ExperienceController.tag)
@RestController
@RequestMapping("/api/profile/experience/")
public class ExperienceController {
	public static final String tag = "Experience Controller";
	private final ExperienceService experienceService;

	@Autowired
	public ExperienceController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}

	@ApiOperation(value = "Returns all Experiences of the user", response = ExperienceDto.class)
	@GetMapping("/{userId}")
	public List<ExperienceDto> getExperiences(@PathVariable Long userId) {
		return experienceService.getUsersExperiences(userId);
	}

	@ApiOperation(value = "Returns all Experiences of the user", response = ExperienceDto.class)
	@GetMapping("/")
	public List<ExperienceDto> getExperiences() {
		return experienceService.getExperiences();
	}

	@ApiOperation(value = "Creates a new Experience", response = ExperienceDto.class)
	@PostMapping("/")
	public ExperienceDto createExperiences(@Valid @RequestBody ExperienceRequestDto experienceRequestDto) {
		return experienceService.createExperience(experienceRequestDto);
	}

	@PutMapping("/{experienceId}")
	@ApiOperation(value = "Experience", notes = "Updates an experience of the User", response = ExperienceDto.class)
	public ExperienceDto updateExperience(@PathVariable Long experienceId, @Valid @RequestBody ExperienceRequestDto experienceRequestDto) throws Exception {

		return experienceService.updateExperience(experienceId, experienceRequestDto);

	}

	@ApiOperation(value = "Deletes a Users  Experience", response = ExperienceDto.class)
	@DeleteMapping("/{experienceId}")
	public void deleteExperience(@PathVariable Long experienceId) throws Exception {
		experienceService.removeExperience(experienceId);
	}

}