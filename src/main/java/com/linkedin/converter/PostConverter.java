package com.linkedin.converter;

import com.linkedin.entities.database.Post;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostConverter {
	private final UserConverter userConverter;

	@Autowired
	public PostConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	public PostDto toPostDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setPostId(post.getPostId());
		postDto.setPostDate(post.getPostDate());
		postDto.setContext(post.getContext());
		postDto.setCreator(userConverter.toUserSimpleDto(post.getCreatorId()));
		postDto.setVisible(post.getVisible());
		postDto.setNumberOfLikes(post.getNumberOfLikes());
		postDto.setImagePath(resolveImagePath(post));
		return postDto;
	}

	public String resolveImagePath(Post post) {
		return Optional.of(post.getImagePath())
						.map(FileService::getFileFullUrl)
						.orElse("");
	}
}
