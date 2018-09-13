package com.linkedin.service;

import com.linkedin.converter.JobConverter;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.model.jobs.JobDto;
import com.linkedin.entities.model.jobs.JobRequestDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final JobConverter jobConverter;
    private final UserService userService;

    @Autowired
    public JobService(JobRepository jobRepository, JobConverter jobConverter, UserService userService) {
        this.jobRepository = jobRepository;
        this.jobConverter = jobConverter;
        this.userService = userService;
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
            //we check if the User that tries to erase the Job is re author of the Job
            Login login = AuthenticationFacade.authenticatedUser();
            Long  userId = login.getUserId();

            Job job = jobRepository.findById(jobId).orElse(null);
            if (job.getAuthorId() != userId) {
                throw new NotAuthorizedException(Job.class);
            }

            jobRepository.deleteById(jobId);
        } else {
            throw new ObjectNotFoundException(Job.class,jobId);

        }


    }

    public JobDto updateJob(Long jobId,JobRequestDto jobRequestDto) throws Exception {

    ///first we check if the job exists in our database
        //we check if the User that tries update the Job is re author of the Job
        if (existsJob(jobId)) {

            Login login = AuthenticationFacade.authenticatedUser();
            Long  userId = login.getUserId();

            Job job = jobRepository.findById(jobId).orElse(null);
            if (job.getAuthorId() != userId) {
                throw new NotAuthorizedException(Job.class);
            }

            //job = new Job();
            job.setJobId(jobId);
            job.setCompany(jobRequestDto.getCompany());
            job.setDescription(jobRequestDto.getDescription());
            job.setTitle(jobRequestDto.getTitle());

            jobRepository.save(job);
            return jobConverter.toJobDto(job);


        } else {
            throw new ObjectNotFoundException(Job.class,jobId);
        }


    }




    public boolean existsJob(Long jobId) {
        return jobRepository.existsById(jobId);
    }

    public JobDto getJob(Long jobId) throws Exception {

        return jobRepository.findById(jobId)
                .map(jobConverter::toJobDto)
                .orElseThrow(() -> new ObjectNotFoundException(Job.class, jobId));
    }
}
