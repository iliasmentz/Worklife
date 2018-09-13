package com.linkedin.service;

import com.linkedin.converter.ExperienceConverter;
import com.linkedin.entities.database.Experience;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.repo.ExperienceRepository;
import com.linkedin.entities.model.Experience.ExperienceDto;
import com.linkedin.entities.model.Experience.ExperienceRequestDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExperienceService {

    private ExperienceRepository experienceRepository;
	private ExperienceConverter experienceConverter;


    @Autowired
    public void ExperienceService(ExperienceRepository experienceRepository, ExperienceConverter experienceConverter){

	  this.experienceRepository = experienceRepository;
	  this.experienceConverter = experienceConverter;
	}


  	public List<ExperienceDto> getExperiences(){
	  Login login = AuthenticationFacade.authenticatedUser();
	  Long  userId = login.getUserId();

	  return experienceRepository.findByUserId(userId).stream()
		  .map(experienceConverter::toExperienceDto)
		  .collect(Collectors.toList());



	}

  public ExperienceDto createExperience(ExperienceRequestDto experienceRequestDto) {

	Login login = AuthenticationFacade.authenticatedUser();
	Long  userId = login.getUserId();

	Experience experience = new Experience();
	experience.setCompany(experienceRequestDto.getCompany());
	experience.setEndingDate(experienceRequestDto.getEndDate());
	experience.setStartingDate(experienceRequestDto.getStartDate()
	);
	experience.setTitle(experienceRequestDto.getTitle());
	experience.setUserId(userId);

	experienceRepository.save(experience);
	return experienceConverter.toExperienceDto(experience);



  }

  public ExperienceDto removeExperience(Long experienceId) throws Exception{


      if(experienceRepository.existsById(experienceId)){
		Login login = AuthenticationFacade.authenticatedUser();
		Long  userId = login.getUserId();

	  }
	  else{
		throw new ObjectNotFoundException(Experience.class,experienceId);

	  }


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
}
