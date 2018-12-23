package com.linkedin.features.posts;

import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.model.post.PostDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = HomeController.tag)
@RestController
@RequestMapping("/api/home/")
public class HomeController {
  public static final String tag = "HomeController";
  private final HomeService homeService;

  @Autowired
  public HomeController(HomeService homeService) {
    this.homeService = homeService;
  }


  @ApiOperation(value = "Returns all Post we want for Home", response = PostDto.class, responseContainer = "List")
  @GetMapping("/")
  public List<PostDto> getHomePosts() {
    return homeService.getHomePosts(AuthenticationFacade.getUserId());
  }

}
