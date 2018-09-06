package com.linkedin.service;

import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JobService {
	private final JobRepository jobRepository;

	@Autowired
	public JobService(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	public Job createJob(JobRequestDto dto) {
		Job job = new Job();
		job.setTitle(dto.getTitle());
		job.setCompany(dto.getCompany());
		job.setAuthorId(AuthenticationFacade.getUserId());
		job.setDescription(dto.getDescription());
		job.setDate(new Date());

		jobRepository.save(job);

		return job;
	}
}
