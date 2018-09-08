package com.linkedin.controller;

import com.linkedin.converter.JobConverter;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.service.JobService;
import com.linkedin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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



	@ApiOperation(value = "Returns all jobs", response = JobDto.class , responseContainer = "List")
	@GetMapping("/")
	public List<JobDto> returnJobs()
	{
		return jobService.getJobs();
	}


	@ApiOperation(value = "Returns a single job", response = JobDto.class)
	@GetMapping("/{jobId}")
	public JobDto returnJobs(@PathVariable Long jobId) throws Exception
	{
		return jobService.getJob(jobId);
	}


	@ApiOperation(value = "Deletes a single Job", response = JobDto.class , responseContainer = "List")
	@DeleteMapping("/{jobId}")
	public void deleteJob(@PathVariable Long jobId) throws Exception
	{
		jobService.removeJob(jobId);
	}
}
