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
  public CommentService(CommentRepository commentRepository,
      CommentConverter commentConverter,
      PostRepository postRepository,
      NotificationService notificationService,
      UserRepository userRepository) {
    this.commentRepository = commentRepository;
    this.commentConverter = commentConverter;
    this.postRepository = postRepository;
    this.notificationService = notificationService;
    this.userRepository = userRepository;
  }

  public List<CommentDto> getPostUserComments(Long userId) throws Exception {
    checkIfUserExists(userId);
    return commentRepository.findAllByCommenterId(userId).stream().map(commentConverter::toCommentDto).collect(Collectors.toList());
  }

  private void checkIfUserExists(Long userId) throws ObjectNotFoundException {
    if (!userRepository.existsById(userId)) {
      throw new ObjectNotFoundException(User.class, userId);
    }
  }

  List<CommentDto> getPostComments(Long postId) throws Exception {
    checkIfPostsExists(postId);

    return commentRepository.findAllByPostId(postId)
      .stream()
      .map(commentConverter::toCommentDto)
      .collect(Collectors.toList());
  }

  CommentDto createNewComment(CommentRequestDto commentRequestDto) throws Exception {
    checkIfPostsExists(commentRequestDto.getPostId());
    Comment comment = commentConverter.toComment(commentRequestDto);
    commentRepository.save(comment);
    sendNotificationToCreator(commentRequestDto, comment);

    return commentConverter.toCommentDto(comment);
  }

  private void sendNotificationToCreator(CommentRequestDto commentRequestDto, Comment comment) {
    Long targetUserId = postRepository.findById(commentRequestDto.getPostId()).get().getCreatorId(); //to userId aytou pou egrapse to
    notificationService.createNotification(targetUserId, 1, comment.getCommentId());
  }

  private void checkIfPostsExists(Long postId) throws ObjectNotFoundException {
    if (!postRepository.existsById(postId)) {
      throw new ObjectNotFoundException(Post.class, postId);
    }
  }

  List<CommentDto> getAllComments() {
    return commentRepository.findAll()
      .stream()
      .map(commentConverter::toCommentDto)
      .collect(Collectors.toList());

  }

  void deleteComment(Long commentId) throws Exception {
    checkIfCommentExists(commentId);
    Comment comment = commentRepository.findById(commentId).orElse(null);
    checkIfCommentBelongsToUser(comment);
    commentRepository.deleteById(commentId);
  }

  CommentDto updateComment(Long commentId, CommentRequestDto commentRequestDto) throws Exception {
    checkIfCommentExists(commentId);
    Comment comment = commentRepository.findById(commentId).orElse(null);
    checkIfCommentBelongsToUser(comment);
    comment.setContext(commentRequestDto.getContext());
    commentRepository.save(comment);
    return commentConverter.toCommentDto(comment);
  }

  private void checkIfCommentExists(Long commentId) throws ObjectNotFoundException {
    if (!commentRepository.existsById(commentId)) {
      throw new ObjectNotFoundException(Comment.class, commentId);
    }
  }

  private void checkIfCommentBelongsToUser(Comment comment) throws NotAuthorizedException {
    Long userId = AuthenticationFacade.getUserId();
    if (!userId.equals(comment.getCommenterId())) {
      throw new NotAuthorizedException(Comment.class);
    }
  }
}
