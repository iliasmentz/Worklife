package com.linkedin.converter;

import com.linkedin.entities.database.Comment;
import com.linkedin.entities.model.Comment.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
	private final UserConverter userConverter;

	@Autowired
	public CommentConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	public CommentDto toCommentDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setCommentId(comment.getCommentId());
		commentDto.setCommentDate(comment.getCommentDate());
		commentDto.setContext(comment.getContext());
		commentDto.setCommenter(userConverter.toUserSimpleDto(comment.getCommenterId()));
		commentDto.setPostId(comment.getPostId());
		return commentDto;
	}


}
