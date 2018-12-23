package com.linkedin.features.education;

import com.linkedin.entities.Education;
import com.linkedin.model.education.EducationDto;
import com.linkedin.model.education.EducationRequestDto;
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

;

@Api(tags = EducationController.tag)
@RestController
@RequestMapping("/api/profile/")
public class EducationController {
  public static final String tag = "Education Controller";

  private final EducationConverter educationConverter;
  private final EducationService educationService;

  @Autowired
  public EducationController(EducationConverter educationConverter, EducationService educationService) {
    this.educationConverter = educationConverter;
    this.educationService = educationService;
  }

  @ApiOperation(value = "Returns Education ", response = EducationDto.class)
  @GetMapping("/education")
  public List<EducationDto> getEducation() {
    return educationService.getUsersEducation();
  }

  @ApiOperation(value = "Returns another User Education ", response = EducationDto.class)
  @GetMapping("/education/{userId}")
  public List<EducationDto> getUsersEducation(@PathVariable Long userId) {
    return educationService.getUsersEducation(userId);
  }

  @ApiOperation(value = "Adds a Education to hist profile and returns the New educationDto ", response = EducationDto.class)
  @PostMapping("/education")
  public EducationDto createEducation(@Valid @RequestBody EducationRequestDto educationRequestDto) {
    Education education = educationService.createEducation(educationRequestDto);
    return educationConverter.toEducationDto(education);
  }

  @ApiOperation(value = "Makes changes ton an existing education of our user (An den yparxei to educationId epistrefei null) ", response = EducationDto.class)
  @PutMapping("/education/{educationId}")
  public EducationDto changeEducation(@Valid @RequestBody EducationRequestDto educationRequestDto, @PathVariable Long educationId) throws Exception {
    Education education = educationService.changeEducation(educationRequestDto, educationId);
    if (education == null) {
      return null;
    }
    return educationConverter.toEducationDto(education);
  }

  @ApiOperation(value = "Deletes an Education from profile ", response = EducationDto.class)
  @DeleteMapping("/education/{educationId}")
  public void deleteEducation(@PathVariable Long educationId) throws Exception {
    educationService.removeEducation(educationId);
  }

}
