package com.linkedin.converter;

import com.linkedin.entities.database.Like;
import com.linkedin.entities.model.likes.LikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeConverter {

	private final UserConverter userConverter;

	@Autowired
	public LikeConverter(UserConverter userConverter) {
		this.userConverter = userConverter;
	}

	public LikeDto toLikeDto(Like like) {
		LikeDto likeDto = new LikeDto();
		likeDto.setLikeId(like.getLikeId());
		likeDto.setPostId(like.getPostId());
		likeDto.setUser(userConverter.toUserSimpleDto(like.getUserId()));
		return likeDto;
	}

}
