package com.linkedin.converter;

import com.linkedin.entities.database.JobApplication;
import com.linkedin.entities.model.jobApplication.JobApplicationDto;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationConverter {
  public JobApplicationDto toJobApplicationDto(JobApplication jobApplication){
    JobApplicationDto jobApplicationDto = new JobApplicationDto();
    jobApplicationDto.setJobId(jobApplication.getJobId());
    jobApplicationDto.setUserId(jobApplication.getUserId());
    return jobApplicationDto;
  }
}
