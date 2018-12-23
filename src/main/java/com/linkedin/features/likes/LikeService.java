package com.linkedin.features.likes;

import com.linkedin.config.errors.NotAuthorizedException;
import com.linkedin.config.errors.ObjectNotFoundException;
import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Like;
import com.linkedin.entities.Post;
import com.linkedin.entities.repo.LikeRepository;
import com.linkedin.entities.repo.PostRepository;
import com.linkedin.features.notifications.NotificationService;
import com.linkedin.model.LikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LikeService {
  private final LikeRepository likeRepository;
  private final LikeConverter likeConverter;
  private final PostRepository postRepository;
  private final NotificationService notificationService;

  @Autowired
  public LikeService(LikeRepository likeRepository, LikeConverter likeConverter, PostRepository postRepository, NotificationService notificationService) {
    this.likeRepository = likeRepository;
    this.likeConverter = likeConverter;
    this.postRepository = postRepository;
    this.notificationService = notificationService;
  }


  public LikeDto createNewLike(Long postId) throws Exception {
    if (!postRepository.existsById(postId)) {
      throw new ObjectNotFoundException(Post.class, postId);
    }

    Long userId = AuthenticationFacade.authenticatedUser().getUserId();
    Like like = new Like();
    System.out.println(like.getLikeId());
    like.setUserId(userId);
    like.setPostId(postId);

    Post post = postRepository.findById(postId).orElse(null);

    post.setNumberOfLikes(post.getNumberOfLikes() + 1);//increase number of likes for this specific post
    postRepository.save(post);
    likeRepository.save(like);

    Long targetUserId = postRepository.findById(post.getPostId()).get().getCreatorId();//o creator tou post pou ginetai to Like
    notificationService.createNotification(targetUserId, 0, like.getLikeId());

    return likeConverter.toLikeDto(like);
  }

  public List<LikeDto> getPostLikes(Long postId) {
    return likeRepository.findAllByPostId(postId)
        .stream()
        .map(likeConverter::toLikeDto)
        .collect(Collectors.toList());
  }

  public List<LikeDto> getUserLikes(Long userId) {
    return likeRepository.findAllByUserId(userId)
        .stream()
        .map(likeConverter::toLikeDto)
        .collect(Collectors.toList());
  }

  public void deleteLike(Long likeId) throws ObjectNotFoundException, NotAuthorizedException {
    if (!likeRepository.existsById(likeId)) {
      throw new ObjectNotFoundException(Like.class, likeId);
    }
    Long userId = AuthenticationFacade.authenticatedUser().getUserId();
    Like likeToUpdate = likeRepository.findById(likeId).orElse(null);
    if (!userId.equals(likeToUpdate != null ? likeToUpdate.getUserId() : null)) {
      throw new NotAuthorizedException(Like.class);
    }
    Post post = postRepository.findById(likeToUpdate.getPostId()).orElse(null);
    //decrease number of likes for this specific post
    post.setNumberOfLikes(Objects.requireNonNull(post).getNumberOfLikes() - 1);
    postRepository.save(post);
    likeRepository.deleteById(likeId);
  }
}
