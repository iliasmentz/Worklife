package com.linkedin.controller;

import com.linkedin.entities.model.Experience.ExperienceDto;
import com.linkedin.entities.model.Experience.ExperienceRequestDto;
import com.linkedin.service.ExperienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@Api(tags = ExperienceController.tag)
@RestController
@RequestMapping("/api/profile/experience/")
public class ExperienceController {
  public static final String tag = "Experience Controller";


  public final ExperienceService experienceService;


  @Autowired
  public ExperienceController(ExperienceService experienceService) {

	this.experienceService = experienceService;
  }


  @ApiOperation(value = "Rerutns all Experiences of the user", response = ExperienceDto.class)
  @GetMapping("/")
  public List<ExperienceDto> getExperiences(){
		return  experienceService.getExperiences();
  }

  @ApiOperation(value = "Rerutns all Experiences of the user", response = ExperienceDto.class)
  @GetMapping("/{userId}")
  public List<ExperienceDto> getUsersExperiences(@PathVariable Long userId){
    return  experienceService.getUsersExperiences(userId);
  }


  @ApiOperation(value = "Creates a new Experience", response = ExperienceDto.class)
  @PostMapping("/")
  public ExperienceDto createExperiences(@Valid @RequestBody ExperienceRequestDto experienceRequestDto){

    return  experienceService.createExperience(experienceRequestDto);
  }

  @ApiOperation(value = "Deletes a Users  Experience", response = ExperienceDto.class)
  @DeleteMapping("/{experienceId}")
  public  void deleteExperience(@PathVariable Long experienceId) throws  Exception{

	experienceService.removeExperience(experienceId);
  }

}