package com.linkedin.controller;

import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.Post.RequestPostDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	AuthenticationFacade.authenticatedUser();
	return postService.getAllPosts();
  }

  @PostMapping("/")
  @ApiOperation(value = "Posts", notes = "Creates a new Post", response = PostDto.class)
  public PostDto createNewPost(@RequestBody RequestPostDto requestPostDto) {
	return postService.createNewPost(requestPostDto);
  }

  @PutMapping("/{postId}")
  @ApiOperation(value = "Posts", notes = "Updates a  Post", response = PostDto.class)
  public PostDto updatePost(@Valid @PathVariable Long postId, @RequestBody RequestPostDto requestPostDto) throws Exception {
	return postService.updatePost(postId, requestPostDto);
  }

}
