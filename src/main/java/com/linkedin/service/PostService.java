package com.linkedin.service;

import com.linkedin.converter.PostConverter;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.Post.PostRequestDto;
import com.linkedin.entities.model.UploadFileResponse;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final PostConverter postConverter;
	private final FileService fileService; 

	@Autowired
	public PostService(PostRepository postRepository, UserRepository userRepository, PostConverter postConverter, FileService fileService) {

		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.postConverter = postConverter;
		this.fileService = fileService;
	}

	public List<PostDto> getAllPosts() {
		return postRepository.findAll()
						.stream()
						.map(postConverter::toPostDto)
						.collect(Collectors.toList());

	}

	public PostDto createNewPostWithFile(PostRequestDto postRequestDto, MultipartFile file) {
		Long newPostId = createNewPost(postRequestDto).getPostId();

		UploadFileResponse uploadFileResponse = fileService.uploadFile(file, "post-", true);
		Post post = postRepository.getOne(newPostId);
		post.setImagePath(uploadFileResponse.getFileName());
		post.setFileType(uploadFileResponse.getFileType());
		postRepository.save(post);
		return postConverter.toPostDto(post);
	}

	public PostDto createNewPost(PostRequestDto postRequestDto) {

		Long userId = AuthenticationFacade.authenticatedUser().getUserId();
		Post post = new Post();

		post.setContext(postRequestDto.getContext());
		post.setCreatorId(userId);
		post.setPostDate(new Date());
		post.setVisible(postRequestDto.getVisible());
		Integer zero = 0;
		post.setNumberOfLikes(zero.longValue());

		postRepository.save(post);
		return postConverter.toPostDto(post);
	}

	public PostDto updatePost(Long postId, PostRequestDto postRequestDto) throws Exception {
		if (!postRepository.existsById(postId)) {
			throw new ObjectNotFoundException(Post.class, postId);
		}
		Long userId = AuthenticationFacade.authenticatedUser().getUserId();
		Post postToUpdate = postRepository.findById(postId).orElse(null);
		if (!userId.equals(postToUpdate != null ? postToUpdate.getCreatorId() : null)) {
			throw new NotAuthorizedException(Post.class);
		}
		postToUpdate.setVisible(postRequestDto.getVisible());
		postToUpdate.setContext(postRequestDto.getContext());
		postToUpdate.setCreatorId(userId);
		postRepository.save(postToUpdate);
		return postConverter.toPostDto(postToUpdate);
	}

	public void deletePost(Long postId) throws Exception {
		if (!postRepository.existsById(postId)) {
			throw new ObjectNotFoundException(Post.class, postId);
		}
		Long userId = AuthenticationFacade.authenticatedUser().getUserId();
		Post postToUpdate = postRepository.findById(postId).orElse(null);
		if (!userId.equals(postToUpdate != null ? postToUpdate.getCreatorId() : null)) {
			throw new NotAuthorizedException(Post.class);
		}
		postRepository.deleteById(postId);
	}

	public PostDto getPost(Long postId) throws Exception {
		if (!postRepository.existsById(postId)) {
			throw new ObjectNotFoundException(Post.class, postId);
		}
		Post post = postRepository.findById(postId).orElse(null);
		return postConverter.toPostDto(post);
	}

	public List<PostDto> getUsersPost(Long userId) throws Exception {
		if (!userRepository.existsById(userId)) {
			throw new ObjectNotFoundException(User.class, userId);
		}
		return postRepository.findAllByCreatorIdOrderByPostDateDesc(userId)
						.stream()
						.map(postConverter::toPostDto)
						.collect(Collectors.toList());
	}


}