package com.linkedin.service;

import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Login;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.JobRepository;
import com.linkedin.entities.database.repo.LoginRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.AdminXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AdminService {
  private final UserService userService;
  private final UserConverter userConverter;
  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final PostService postService;
  private final ExperienceService experienceService;
  private final CommentService commentService;
  private final LikeService likeService;
  private final ConnectionService connectionService;
  private final JobService jobService;
  private final JobRepository jobRepository;
  private final LoginRepository loginRepository;

  @Autowired
  public AdminService(UserService userService, UserConverter userConverter, UserRepository userRepository, PostRepository postRepository, PostService postService, ExperienceService experienceService, CommentService commentService, LikeService likeService, ConnectionService connectionService, JobService jobService, JobRepository jobRepository, LoginRepository loginRepository){
	this.userService = userService;
	this.userConverter = userConverter;
	this.userRepository = userRepository;
	this.postRepository = postRepository;
	this.postService = postService;
	this.experienceService = experienceService;
	this.commentService = commentService;
	this.likeService = likeService;
	this.connectionService = connectionService;
	this.jobService = jobService;
	this.jobRepository = jobRepository;
	this.loginRepository = loginRepository;
  }

  public List<AdminXmlDto> getXml() throws  Exception {
    List <User> userList = userRepository.findAll();
	List <AdminXmlDto> adminXmlDtoList = new ArrayList<>();

    for(int i =0;i < userList.size();++i){
      Long userId = userList.get(i).getId();
	  AdminXmlDto adminXmlDto = new AdminXmlDto();


	  adminXmlDto.setUserId(userId);
	  User user = userRepository.findById(userId).orElse(null);
	  Login login = loginRepository.findByUserId(userId).orElse(null);

	  adminXmlDto.setPosts(postService.getUsersPost(userId));
	  adminXmlDto.setExperiences(experienceService.getUsersExperiences(userId));
	  adminXmlDto.setUserDto( userConverter.toUserDto(userRepository.findById(userId).orElse(null) , login.getRole().ordinal()));
	  adminXmlDto.setComments(commentService.getPostUserComments(userId));
	  adminXmlDto.setLikes(likeService.getUserLikes(userId));
	  adminXmlDto.setConnectionDtoList(connectionService.getUserConnections(userId));
	  adminXmlDto.setJobs(jobService.getAllJobUserCreated(userId));
      adminXmlDtoList.add(adminXmlDto);

	}
	return adminXmlDtoList;

  }
}
