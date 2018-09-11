package com.linkedin.controller;

import com.linkedin.converter.EducationConverter;
import com.linkedin.converter.JobConverter;
import com.linkedin.converter.UserConverter;

import com.linkedin.entities.database.Education;
import com.linkedin.entities.database.repo.EducationRepository;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.education.EducationDto;
;
import com.linkedin.entities.model.education.EducationRequestDto;
import com.linkedin.service.EducationService;
import com.linkedin.service.JobService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = EducationController.tag)
@RestController
@RequestMapping("/api/profile/")
public class EducationController {
    public static final String tag = "Education Controller";
    public final EducationRepository educationRepository;

    private final EducationConverter educationConverter;
    private final EducationService educationService;

    @Autowired
    public EducationController(JobService jobService, JobConverter jobConverter, UserService userService, EducationConverter educationConverter, UserConverter userConverter, UserService userService1, EducationRepository educationRepository, EducationService educationService) {

        this.educationConverter = educationConverter;

        this.educationRepository = educationRepository;
        this.educationService = educationService;
    }

    @ApiOperation(value = "Returns Education " ,  response = EducationDto.class)
    @GetMapping("/education")
    public List<EducationDto> getEducation() {

        return educationService.getUsersEducation();


  }

    @ApiOperation(value = "Adds a Education to hist profile and returns the New educationDto " ,  response = EducationDto.class)
    @PostMapping("/education")
    public EducationDto createEducation(EducationRequestDto educationRequestDto) {


        Education education = educationService.createEducation(educationRequestDto);
        return educationConverter.toEducationDTO(education);


    }

    @ApiOperation(value = "Makes changes ton an existing education of our user (An den yparxei to educationId epistrefei null) " ,  response = EducationDto.class)
    @PutMapping ("/education/{educationId}")
    public EducationDto changeEducation(EducationRequestDto educationRequestDto,@PathVariable Long educationId) {


        Education education = educationService.changeEducation(educationRequestDto,educationId);
        if (education == null)return null;
        return educationConverter.toEducationDTO(education);


    }

    @ApiOperation(value = "Deletes an Education from profile " ,  response = EducationDto.class)
    @DeleteMapping ("/education/{educationId}")
    public void deleteEducation(@PathVariable Long educationId) {

        if(! educationRepository.existsById(educationId)) return ;

        educationRepository.deleteById(educationId);

    }



}
