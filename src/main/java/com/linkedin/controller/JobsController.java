package com.linkedin.controller;

import com.linkedin.converter.JobConverter;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.entities.model.jobApplication.JobApplicationDto;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.service.JobService;
import com.linkedin.service.UserService;
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

@Api(tags = JobsController.tag)
@RestController
@RequestMapping("/api/jobs")
public class JobsController {
  public static final String tag = "Jobs Controller";

  private final JobService jobService;
  private final JobConverter jobConverter;

  @Autowired
  JobRepository jobRepository;

  @Autowired
  public JobsController(JobService jobService, JobConverter jobConverter, UserService userService) {
	this.jobService = jobService;
	this.jobConverter = jobConverter;
  }

  @ApiOperation(value = "Creates a new job", response = JobDto.class)
  @PostMapping("/")
  public JobDto createNewJob(@Valid @RequestBody JobRequestDto jobRequestDto) {

	Job job = jobService.createJob(jobRequestDto);

	return jobConverter.toJobDto(job);
  }


  @ApiOperation(value = "Returns all jobs", response = JobDto.class, responseContainer = "List")
  @GetMapping("/")
  public List<JobDto> returnJobs() {
	return jobService.getJobs();
  }


  @ApiOperation(value = "Returns a single job", response = JobDto.class)
  @GetMapping("/{jobId}")
  public JobDto returnJobs(@PathVariable Long jobId) throws Exception {
	return jobService.getJob(jobId);
  }


  @ApiOperation(value = "Deletes a single Job (You have to be the Jobs Author otherwise you get a not Authorized error)", response = JobDto.class, responseContainer = "List")
  @DeleteMapping("/{jobId}")
  public void deleteJob(@PathVariable Long jobId) throws Exception {
	jobService.removeJob(jobId);
  }

  @ApiOperation(value = "Update properties of  a single Job (You have to be the Jobs Author otherwise you get a not Authorized error)", response = JobDto.class, responseContainer = "List")
  @PutMapping("/{jobId}")
  public JobDto updateJob(@PathVariable Long jobId, @Valid @RequestBody JobRequestDto jobRequestDto) throws Exception {
	return jobService.updateJob(jobId, jobRequestDto);
  }

  @ApiOperation(value = "Apply to a single Job", response = JobApplicationDto.class)
  @PostMapping("/apply/{jobId}")
  public JobApplicationDto applyToJob(@PathVariable Long jobId) throws Exception {
	return jobService.applyToJob(jobId);
  }

  @ApiOperation(value = "Returns all the JobApplications made from all Users from database ", response = JobApplicationDto.class)
  @GetMapping("/apply/")
  public List<JobApplicationDto> getJobApplications() {
	return jobService.getJobApplications();
  }

  @ApiOperation(value = "Returns all the JobApplications made from logged User ", response = JobApplicationDto.class)
  @GetMapping("/apply/myapplications")
  public List<JobApplicationDto> getyMJobApplications() throws Exception {
	return jobService.getyMJobApplications();
  }

	@ApiOperation(value = "Returns all the JobApplications made from logged User ", response = UserSimpleDto.class)
  @GetMapping("/apply/{jobId}/applicants/")
  public List<UserSimpleDto> getyMJobApplicants(@PathVariable Long jobId) throws Exception {
	return jobService.getyMJobApplicants(jobId);
  }
}
