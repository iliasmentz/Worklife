package com.linkedin.converter;

import com.linkedin.entities.database.Comment;
import com.linkedin.entities.model.Comment.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

  public CommentDto toCommentDto(Comment comment){
	CommentDto commentDto = new CommentDto();
	commentDto.setCommentId(comment.getCommentId());
	commentDto.setCommentDate(comment.getCommentDate());
	commentDto.setContext(comment.getContext());
	commentDto.setCommenterId(comment.getCommenterId());
	commentDto.setPostId(comment.getPostId());
	return commentDto;
  }


}
