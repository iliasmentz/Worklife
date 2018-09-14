package com.linkedin.service;

import com.linkedin.converter.PostConverter;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.Skill;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.Post.RequestPostDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
  private PostRepository postRepository;
  private PostConverter postConverter;

  @Autowired
  public PostService(PostRepository postRepository, PostConverter postConverter) {

	this.postRepository = postRepository;
	this.postConverter = postConverter;
  }

  public List<PostDto> getAllPosts() {
	return postRepository.findAll()
		.stream()
		.map(postConverter::toPostDto)
		.collect(Collectors.toList());

  }

  public PostDto createNewPost(RequestPostDto requestPostDto) {

	Long userId = AuthenticationFacade.authenticatedUser().getUserId();
	Post post = new Post();

	post.setContext(requestPostDto.getContext());
	post.setCreatorId(userId);
	post.setPostDate(requestPostDto.getPostDate());
	post.setVisible(requestPostDto.getVisible());

	postRepository.save(post);
	return postConverter.toPostDto(post);
  }

  public PostDto updatePost(Long postId, RequestPostDto requestPostDto) throws Exception {
	if (!postRepository.existsById(postId)) {
	  throw new ObjectNotFoundException(Post.class, postId);
	}

	Long userId = AuthenticationFacade.authenticatedUser().getUserId();
	Post postToUpdate = postRepository.findById(postId).orElse(null);
	if (!userId.equals(postToUpdate != null ? postToUpdate.getCreatorId() : null)) {
	  throw new NotAuthorizedException(Post.class);
	}

	postToUpdate.setVisible(requestPostDto.getVisible());
	postToUpdate.setPostDate(requestPostDto.getPostDate());
	postToUpdate.setContext(requestPostDto.getContext());
	postToUpdate.setCreatorId(userId);

	return postConverter.toPostDto(postToUpdate);


  }
}