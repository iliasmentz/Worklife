package com.linkedin.controller;

import com.linkedin.entities.model.likes.LikeDto;
import com.linkedin.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = LikeController.tag)
@RestController
@RequestMapping("/api/likes/")
public class LikeController {
	public static final String tag = "Likes Controller";
	private final LikeService likeService;

	@Autowired
	public LikeController(LikeService likeService) {

		this.likeService = likeService;
	}

	@ApiOperation(value = "Creates a new like for a specific post", response = LikeDto.class)
	@PostMapping("/{postId}")
	public LikeDto createNewLike(@PathVariable Long postId) throws Exception {
		return likeService.createNewLike(postId);
	}

	@ApiOperation(value = "Returns all Likes of a Post", response = LikeDto.class)
	@GetMapping("/{postId}")
	public List<LikeDto> getPostLikes(@PathVariable Long postId) {
		return likeService.getPostLikes(postId);
	}

	@ApiOperation(value = "Returns all Likes of a User", response = LikeDto.class)
	@GetMapping("/user/{userId}")
	public List<LikeDto> getUserLikes(@PathVariable Long userId) {
		return likeService.getUserLikes(userId);
	}

	@ApiOperation(value = "Remove a like from a post")
	@DeleteMapping("/{likeId}")
	public void deleteLike(@PathVariable Long likeId) throws Exception {
		likeService.deleteLike(likeId);
	}
}
