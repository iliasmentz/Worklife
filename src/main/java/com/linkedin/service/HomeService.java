package com.linkedin.service;

import com.linkedin.converter.LikeConverter;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.repo.ConnectionRepository;
import com.linkedin.entities.database.repo.LikeRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.UserDto;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final LikeRepository likeRepository;
    private final LikeConverter likeConverter;
    private final PostRepository postRepository;
    private final ConnectionRepository connectionRepository;
    private final ConnectionService connectionService;

    @Autowired
    public HomeService(LikeRepository likeRepository, LikeConverter likeConverter, PostRepository postRepository, ConnectionRepository connectionRepository, ConnectionService connectionService, ConnectionService connectionService1){
        this.likeRepository = likeRepository;
        this.likeConverter = likeConverter;
        this.postRepository = postRepository;
        this.connectionRepository = connectionRepository;
        this.connectionService = connectionService1;
    }

    public List<PostDto> getHomePosts() {
        Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();
        //etsi pairnoume ola ta Post tou user pou einai loggedIn
        List<Post> UsersPostList = postRepository.findAllByCreatorIdOrderByPostDateDesc(logedUserId);
        List<UserDto> friendsList = connectionService.getFriendsToUserDto();
        return null;
    }
}
