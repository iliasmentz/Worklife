package com.linkedin.service;

import com.linkedin.converter.JobConverter;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobConverter jobConverter;

    @Autowired
    public JobService(JobRepository jobRepository, JobConverter jobConverter) {
        this.jobRepository = jobRepository;
        this.jobConverter = jobConverter;
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

    public List<JobDto> getJobs() //epistrefei lista me ola ta jobs
    {
        return jobRepository.findAll()
                .stream()
                .map(jobConverter::toJobDto)
                .collect(Collectors.toList());
    }

    public void removeJob(Long jobId) throws Exception {


        if (existsJob(jobId)) {
            jobRepository.deleteById(jobId);
        } else {
            throw new Exception("Object Not Found");
        }

    }

    public boolean existsJob(Long jobId) {
        return jobRepository.findById(jobId) != null;
    }

    public JobDto getJob(Long jobId) throws Exception {

        return jobRepository.findById(jobId)
                .map(jobConverter::toJobDto)
                .orElseThrow(() -> new ObjectNotFoundException(Job.class, jobId));
    }
}
