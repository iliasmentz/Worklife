package com.linkedin.features.jobs;

import com.linkedin.entities.JobApplication;
import com.linkedin.model.jobApplication.JobApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationConverter {
  public JobApplicationDto toJobApplicationDto(JobApplication jobApplication) {
    JobApplicationDto jobApplicationDto = new JobApplicationDto();
    jobApplicationDto.setJobId(jobApplication.getJobId());
    jobApplicationDto.setUserId(jobApplication.getUserId());
    return jobApplicationDto;
  }
}
