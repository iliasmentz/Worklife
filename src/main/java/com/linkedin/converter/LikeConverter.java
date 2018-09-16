package com.linkedin.converter;

import com.linkedin.entities.database.Like;
import com.linkedin.entities.model.Likes.LikeDto;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter {

  public LikeDto toLikeDto(Like like){
    LikeDto likeDto = new LikeDto();
    likeDto.setLikeId(like.getLikeId());
    likeDto.setPostId(like.getPostId());
    likeDto.setUserId(like.getUserId());
    return likeDto;
  }

}
