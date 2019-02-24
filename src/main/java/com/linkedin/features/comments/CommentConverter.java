package com.linkedin.features.comments;

import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Comment;
import com.linkedin.features.users.UserConverter;
import com.linkedin.model.comment.CommentDto;
import com.linkedin.model.comment.CommentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentConverter {
  private final UserConverter userConverter;

  @Autowired
  public CommentConverter(UserConverter userConverter) {
    this.userConverter = userConverter;
  }

  CommentDto toCommentDto(Comment comment) {
    CommentDto commentDto = new CommentDto();
    commentDto.setCommentId(comment.getCommentId());
    commentDto.setCommentDate(comment.getCommentDate());
    commentDto.setContext(comment.getContext());
    commentDto.setCommenter(userConverter.toUserSimpleDto(comment.getCommenterId()));
    commentDto.setPostId(comment.getPostId());
    return commentDto;
  }

  Comment toComment(CommentRequestDto commentRequestDto) {
    Comment comment = new Comment();
    comment.setCommentDate(new Date());
    comment.setCommenterId(AuthenticationFacade.getUserId());
    comment.setContext(commentRequestDto.getContext());
    comment.setPostId(commentRequestDto.getPostId());
    return comment;
  }


}
