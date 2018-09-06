package com.linkedin.controller;

import com.linkedin.converter.JobConverter;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = JobsController.tag)
@RestController
@RequestMapping("/api/jobs")
public class JobsController {
	public static final String tag = "Jobs Controller";

	private final JobService jobService;
	private final JobConverter jobConverter;

	@Autowired
	public JobsController(JobService jobService, JobConverter jobConverter) {
		this.jobService = jobService;
		this.jobConverter = jobConverter;
	}

	@ApiOperation(value = "Creates a new job", response = JobDto.class)
	@PostMapping("/")
	public JobDto createNewJob(@Valid @RequestBody JobRequestDto jobRequestDto) {
		if (jobRequestDto.getId() != null) {
			return null;
		}
		Job job = jobService.createJob(jobRequestDto);

		return jobConverter.toJobDto(job);
	}

}
