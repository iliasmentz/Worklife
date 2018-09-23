package com.linkedin.controller;

import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.Post.PostRequestDto;
import com.linkedin.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = PostController.tag)
@RestController
@RequestMapping("/api/posts/")
public class PostController {
  public static final String tag = "Post Controller";
  private PostService postService;

  @Autowired
  public PostController(PostService postService) {

	this.postService = postService;
  }

  @GetMapping("/")
  @ApiOperation(value = "Posts", notes = "Returns All Posts of all Users", response = PostDto.class)
  public List<PostDto> getAllPosts() {
	return postService.getAllPosts();
  }

  @GetMapping("/{postId}")
  @ApiOperation(value = "Posts", notes = "Returns Single Post", response = PostDto.class)
  public PostDto getSinglePost(@Valid @PathVariable Long postId) throws Exception {
	return postService.getPost(postId);
  }

  @GetMapping("/users/{userId}")
  @ApiOperation(value = "Posts", notes = "Returns All Posts of a single User", response = PostDto.class)
  public List<PostDto> getUsersPosts(@Valid @PathVariable Long userId) throws Exception {
	return postService.getUsersPost(userId);
  }

	@PostMapping("/")
	@ApiOperation(value = "Posts", notes = "Creates a new Post", response = PostDto.class)
	public PostDto createNewPost(@ModelAttribute PostRequestDto postRequestDto) {
		if (postRequestDto.getFile() == null || postRequestDto.getFile().getSize() == 0) {
			return postService.createNewPost(postRequestDto);
		} else {
			return postService.createNewPostWithFile(postRequestDto, postRequestDto.getFile());
		}
	}

  @PutMapping("/{postId}")
  @ApiOperation(value = "Posts", notes = "Updates a  Post", response = PostDto.class)
  public PostDto updatePost(@Valid @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) throws Exception {
	return postService.updatePost(postId, postRequestDto);
  }

  @DeleteMapping("/{postId}")
  @ApiOperation(value = "Posts", notes = "Deletes a  Post", response = PostDto.class)
  public void deletePost(@Valid @PathVariable Long postId) throws Exception {
	postService.deletePost(postId);
  }

}
