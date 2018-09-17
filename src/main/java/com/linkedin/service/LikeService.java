package com.linkedin.service;

import com.linkedin.converter.LikeConverter;
import com.linkedin.entities.database.Like;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.repo.LikeRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.model.Likes.LikeDto;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeService {
  private final LikeRepository likeRepository;
  private final LikeConverter likeConverter;
  private final PostRepository postRepository;

  @Autowired
  public LikeService(LikeRepository likeRepository, LikeConverter likeConverter, PostRepository postRepository) {
	this.likeRepository = likeRepository;
	this.likeConverter = likeConverter;
	this.postRepository = postRepository;
  }



  public LikeDto createNewLike(Long postId) throws Exception {
	if (!postRepository.existsById(postId)) {
	  throw new ObjectNotFoundException(Post.class  , postId);
	}


	Long userId = AuthenticationFacade.authenticatedUser().getUserId();
	Like like = new Like();
	System.out.println(like.getLikeId());
	like.setUserId(userId);
	like.setPostId(postId);


	Post post = postRepository.findById(postId).orElse(null);

	post.setNumberOfLikes(post.getNumberOfLikes()+1 );//increase number of likes for this specific post
	postRepository.save(post);
	likeRepository.save(like);
	return likeConverter.toLikeDto(like);


  }

  public List<LikeDto> getPostLikes(Long postId) {
	return likeRepository.findAllByPostId(postId)
		.stream()
		.map(likeConverter::toLikeDto)
		.collect(Collectors.toList());

  }

}
