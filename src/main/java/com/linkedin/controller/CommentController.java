package com.linkedin.controller;


import com.linkedin.entities.database.Comment;
import com.linkedin.entities.model.Comment.CommentDto;
import com.linkedin.entities.model.Comment.CommentRequestDto;
import com.linkedin.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Api(tags = CommentController.tag)
@RestController
@RequestMapping("/api/comments/")
public class CommentController {
  public static final String tag = "CommentController";
  @Autowired
  private CommentService commentService;

  @GetMapping("/post/")
  @ApiOperation(value = "Comment", notes = "Returns all Comments", response = Comment.class)
  public List<CommentDto> getPostComments(){
    return commentService.getAllComments();
  }

  @GetMapping("/post/{postId}")
  @ApiOperation(value = "Comment", notes = "Returns all Comments of a Post", response = Comment.class)
  public List<CommentDto> getPostComments(@PathVariable Long postId) throws Exception {
	return commentService.getPostComments(postId);
  }

  @PostMapping("/post/")
  @ApiOperation(value = "Comment" , notes = "Creates a new Comment for a Post" , response = Comment.class)
  public CommentDto createNewComment(@Valid @RequestBody CommentRequestDto commentRequestDto)  throws Exception{
      return commentService.createNewComment(commentRequestDto);
  }
  @DeleteMapping ("/post/{commentId}")
  @ApiOperation(value = "Comment" , notes = "Creates a new Comment for a Post" , response = Comment.class)
  public void deleteNewComment(@PathVariable Long commentId)  throws Exception{
    commentService.deleteComment(commentId);
  }

  @PutMapping("/post/{commentId}")
  @ApiOperation(value = "Comment" , notes = "Updates a comment of a Post" , response = Comment.class)
  public CommentDto updateComment(@PathVariable Long commentId ,@Valid @RequestBody CommentRequestDto commentRequestDto)  throws Exception{
    return commentService.updateComment(commentId, commentRequestDto);
  }




}
