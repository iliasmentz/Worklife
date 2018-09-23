package com.linkedin.service;

import com.linkedin.converter.CommentConverter;
import com.linkedin.entities.database.Comment;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.repo.CommentRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.model.Comment.CommentDto;
import com.linkedin.entities.model.Comment.CommentRequestDto;
import com.linkedin.errors.NotAuthorizedException;
import com.linkedin.errors.ObjectNotFoundException;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final CommentConverter commentConverter;
  private final PostRepository postRepository;
  private final NotificationService notificationService;

  @Autowired
  public CommentService(CommentRepository commentRepository, CommentConverter commentConverter, PostRepository postRepository, NotificationService notificationService) {
	this.commentRepository = commentRepository;
	this.commentConverter = commentConverter;
	this.postRepository = postRepository;
	this.notificationService = notificationService;
  }


  public List<CommentDto> getPostComments(Long postId) throws Exception {

	if (!postRepository.existsById(postId)) {
	  throw new ObjectNotFoundException(Post.class, postId);
	}


	return commentRepository.findAllByPostId(postId)
		.stream()
		.map(commentConverter::toCommentDto)
		.collect(Collectors.toList());

  }

  public CommentDto createNewComment(CommentRequestDto commentRequestDto) throws Exception {
	if (!postRepository.existsById(commentRequestDto.getPostId())) {
	  throw new ObjectNotFoundException(Post.class, commentRequestDto.getPostId());
	}


	Comment comment = new Comment();


	Date date = new Date();
	String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);
	comment.setCommentDate(new SimpleDateFormat("dd/MM/yyyy").parse(dateString));
	comment.setCommenterId(AuthenticationFacade.getUserId());
	comment.setContext(commentRequestDto.getContext());
	comment.setPostId(commentRequestDto.getPostId());
	commentRepository.save(comment);
	notificationService.createNotification(AuthenticationFacade.getUserId(),1);


	return commentConverter.toCommentDto(comment);

  }

  public List<CommentDto> getAllComments() {
	return commentRepository.findAll()
		.stream()
		.map(commentConverter::toCommentDto)
		.collect(Collectors.toList());

  }

  public void deleteComment(Long commentId) throws Exception {
	if (!commentRepository.existsById(commentId)) {
	  throw new ObjectNotFoundException(Comment.class, commentId);
	}

	Long userId = AuthenticationFacade.getUserId();
	Comment comment = commentRepository.findById(commentId).orElse(null);
	if (userId != comment.getCommenterId()) {
	  throw new NotAuthorizedException(Comment.class);
	}

	commentRepository.deleteById(commentId);


  }

  public CommentDto updateComment(Long commentId, CommentRequestDto commentRequestDto) throws Exception {
	if (!commentRepository.existsById(commentId)) {
	  throw new ObjectNotFoundException(Comment.class, commentId);
	}
	Long userId = AuthenticationFacade.getUserId();
	Comment comment = commentRepository.findById(commentId).orElse(null);
	if (userId != comment.getCommenterId()) {
	  throw new NotAuthorizedException(Comment.class);
	}

	comment.setContext(commentRequestDto.getContext());
	comment.setCommenterId(userId);
	commentRepository.save(comment);
	return commentConverter.toCommentDto(comment);
  }
}
