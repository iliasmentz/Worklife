package com.linkedin.features.admin;

import com.linkedin.entities.Login;
import com.linkedin.entities.User;
import com.linkedin.entities.repo.LoginRepository;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.features.comments.CommentService;
import com.linkedin.features.connections.ConnectionService;
import com.linkedin.features.experience.ExperienceService;
import com.linkedin.features.jobs.JobService;
import com.linkedin.features.likes.LikeService;
import com.linkedin.features.posts.PostService;
import com.linkedin.features.users.UserConverter;
import com.linkedin.model.AdminXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AdminService {
  private final UserConverter userConverter;
  private final UserRepository userRepository;
  private final PostService postService;
  private final ExperienceService experienceService;
  private final CommentService commentService;
  private final LikeService likeService;
  private final ConnectionService connectionService;
  private final JobService jobService;
  private final LoginRepository loginRepository;

  @Autowired
  public AdminService(UserConverter userConverter, UserRepository userRepository, PostService postService, ExperienceService experienceService, CommentService commentService,
      LikeService likeService, ConnectionService connectionService, JobService jobService, LoginRepository loginRepository) {
    this.userConverter = userConverter;
    this.userRepository = userRepository;
    this.postService = postService;
    this.experienceService = experienceService;
    this.commentService = commentService;
    this.likeService = likeService;
    this.connectionService = connectionService;
    this.jobService = jobService;
    this.loginRepository = loginRepository;
  }

  public List<AdminXmlDto> getXml() throws Exception {
    List<User> userList = userRepository.findAll();
    List<AdminXmlDto> adminXmlDtoList = new ArrayList<>();

    for (int i = 0; i < userList.size(); ++i) {
      Long userId = userList.get(i).getId();
      AdminXmlDto adminXmlDto = new AdminXmlDto();


      adminXmlDto.setUserId(userId);
      User user = userRepository.findById(userId).orElse(null);
      Login login = loginRepository.findByUserId(userId).orElse(null);

      adminXmlDto.setPosts(postService.getUsersPost(userId));
      adminXmlDto.setExperiences(experienceService.getUsersExperiences(userId));
      adminXmlDto.setUserDto(userConverter.toUserDto(userRepository.findById(userId).orElse(null), login.getRole().ordinal()));
      adminXmlDto.setComments(commentService.getPostUserComments(userId));
      adminXmlDto.setLikes(likeService.getUserLikes(userId));
      adminXmlDto.setConnectionDtoList(connectionService.getUserConnections(userId));
      adminXmlDto.setJobs(jobService.getAllJobUserCreated(userId));
      adminXmlDtoList.add(adminXmlDto);

    }
    return adminXmlDtoList;

  }
}
