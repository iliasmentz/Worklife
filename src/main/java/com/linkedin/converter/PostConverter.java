package com.linkedin.converter;

import com.linkedin.entities.database.Post;
import com.linkedin.entities.model.Post.PostDto;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

  public PostDto toPostDto(Post post) {
	PostDto postDto = new PostDto();
	postDto.setPostId(post.getPostId());
	postDto.setPostDate(post.getPostDate());
	postDto.setContext(post.getContext());
	postDto.setCreatorId(post.getCreatorId());
	postDto.setVisible(post.getVisible());
	postDto.setNumberOfLikes(post.getNumberOfLikes());
	return postDto;
  }

}
