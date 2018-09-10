package com.linkedin.controller;

import com.linkedin.converter.JobConverter;
import com.linkedin.converter.UserConverter;

import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.EducationRepository;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.education.EducationDto;
;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.JobService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = EducationController.tag)
@RestController
@RequestMapping("/api/profile/")
public class EducationController {
    public static final String tag = "Education Controller";

    private final JobService jobService;
    private final JobConverter jobConverter;

    @Autowired
    JobRepository jobRepository;
    private UserConverter userConverter;
    private UserService userService;
    private EducationRepository educationRepository;

    @Autowired
    public EducationController(JobService jobService, JobConverter jobConverter, UserService userService, UserConverter userConverter, UserService userService1, EducationRepository educationRepository) {
        this.jobService = jobService;
        this.jobConverter = jobConverter;
        this.userConverter = userConverter;
        this.userService = userService1;
        this.educationRepository = educationRepository;
    }

    @ApiOperation(value = "Returns Education " ,  response = EducationDto.class)
    @GetMapping("/education")
    public EducationDto getEducation() {
        Login login = AuthenticationFacade.authenticatedUser();

        User user =  userService.getUser(login.getUserId());



        return null

    }



}
