package com.linkedin.features.posts;

import com.linkedin.entities.Post;
import com.linkedin.features.files.FileService;
import com.linkedin.features.users.UserConverter;
import com.linkedin.model.post.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    postDto.setFilePath(resolveFilePath(post));
    postDto.setType(post.getFileType());
    return postDto;
  }

  private String resolveFilePath(Post post) {
    if (post.getImagePath() != null) {
      return FileService.getFileFullUrl(post.getImagePath());
    }
    return "";
  }
}
