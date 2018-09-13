package com.linkedin.service;

import com.linkedin.converter.EducationConverter;
import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Education;
import com.linkedin.entities.database.Job;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.repo.EducationRepository;
import com.linkedin.entities.model.education.EducationDto;
import com.linkedin.entities.model.education.EducationRequestDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {
    private EducationRepository educationRepository;
    private EducationConverter educationConverter;



    @Autowired
    public EducationService(EducationRepository educationRepository, UserConverter userConverter, EducationConverter educationConverter) {
        this.educationRepository = educationRepository;
        this.educationConverter = educationConverter;
    }

    //returns list of Education for a the specific User
    public List<EducationDto> getUsersEducation() {
        Login login = AuthenticationFacade.authenticatedUser();

        Long userId = login.getUserId();
        return educationRepository.findByUserId(userId)
                .stream()
                .map(educationConverter::toEducationDTO)
                .collect(Collectors.toList());
    }

    public Education createEducation(EducationRequestDto educationRequestDto){
        Login login = AuthenticationFacade.authenticatedUser();
        Long userId = login.getUserId();

        Education education = new Education();
        education.setUniversity_name(educationRequestDto.getUniversityName());
        education.setUniversityDegree(educationRequestDto.getUniversityDegree());
        education.setUserId(userId);
        education.setEndingDate(educationRequestDto.getEndingDate());
        education.setStartingDate(educationRequestDto.getStartingDate());


        educationRepository.save(education);
        return education;
    }



    public Education changeEducation(EducationRequestDto educationRequestDto, Long educationId) throws Exception {



        if(! educationRepository.existsById(educationId)) {
            throw new ObjectNotFoundException(Education.class, educationId);
        }


        Login login = AuthenticationFacade.authenticatedUser();
        Long userId = login.getUserId();

        //we check if the user that is  not changing anothers user  Education
        Education educationCheck = educationRepository.findById(educationId).orElse(null);
        if(userId   != educationCheck.getUserId() ){
            throw new NotAuthorizedException(Job.class);

        }

        Education education = new Education();
        education.setEducationId(educationId);
        education.setUniversity_name(educationRequestDto.getUniversityName());
        education.setUniversityDegree(educationRequestDto.getUniversityDegree());
        education.setUserId(userId);
        education.setEndingDate(educationRequestDto.getEndingDate());
        education.setStartingDate(educationRequestDto.getStartingDate());


        educationRepository.save(education);
        return education;
    }
}
