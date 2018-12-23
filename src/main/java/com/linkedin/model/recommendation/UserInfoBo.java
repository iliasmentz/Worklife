package com.linkedin.model.recommendation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoBo {
  private Long userId;
  private Long postId;
  private Long postCreatorId;
  private Long numberOfLikes;
  private Long numberOfComments;
  private Long isUserAndPosterFriends;
}
