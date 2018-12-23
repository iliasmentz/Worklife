package com.linkedin.features.comments;

import com.linkedin.config.errors.NotAuthorizedException;
import com.linkedin.config.errors.ObjectNotFoundException;
import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Comment;
import com.linkedin.entities.Post;
import com.linkedin.entities.User;
import com.linkedin.entities.repo.CommentRepository;
import com.linkedin.entities.repo.PostRepository;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.features.notifications.NotificationService;
import com.linkedin.model.comment.CommentDto;
import com.linkedin.model.comment.CommentRequestDto;
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
  private final UserRepository userRepository;

  @Autowired
  public CommentService(CommentRepository commentRepository, CommentConverter commentConverter, PostRepository postRepository, NotificationService notificationService, UserRepository userRepository) {
    this.commentRepository = commentRepository;
    this.commentConverter = commentConverter;
    this.postRepository = postRepository;
    this.notificationService = notificationService;
    this.userRepository = userRepository;
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

    Long targetUserId = postRepository.findById(commentRequestDto.getPostId()).get().getCreatorId(); //to userId aytou pou egrapse to
    notificationService.createNotification(targetUserId, 1, comment.getCommentId());


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

  public List<CommentDto> getPostUserComments(Long userId) throws Exception {
    if (!userRepository.existsById(userId)) {
      throw new ObjectNotFoundException(User.class, userId);
    }
    return commentRepository.findAllByCommenterId(userId).stream().map(commentConverter::toCommentDto).collect(Collectors.toList());

  }
}
