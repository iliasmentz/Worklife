package com.linkedin.service;

import com.linkedin.converter.LikeConverter;
import com.linkedin.entities.database.Like;
import com.linkedin.entities.database.repo.LikeRepository;
import com.linkedin.entities.model.Likes.LikeDto;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
  private final LikeRepository likeRepository;
  private final LikeConverter likeConverter;

  @Autowired
  public LikeService(LikeRepository likeRepository, LikeConverter likeConverter) {
	this.likeRepository = likeRepository;
	this.likeConverter = likeConverter;
  }


  public LikeDto createNewLike(Long postId) {
	Long userId = AuthenticationFacade.authenticatedUser().getUserId();
	Like like = new Like();
	like.setUserId(userId);
	like.setPostId(postId);
	likeRepository.save(like);
	return likeConverter.toLikeDto(like);


  }

  public List<LikeDto> getPostLikes(Long postId) {
	return likeRepository.findAllByPostId(postId)
		.stream()
		.map(likeConverter::toLikeDto)
		.collect(Collectors.toList());

  }

  public Integer getNumberPostLikes(Long postId) {
    return likeRepository.findAllByPostId(postId).size();
  }
}
