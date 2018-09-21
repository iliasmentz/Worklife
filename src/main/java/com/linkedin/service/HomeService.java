package com.linkedin.service;

import com.linkedin.converter.LikeConverter;
import com.linkedin.converter.PostConverter;
import com.linkedin.entities.database.Like;
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
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final LikeRepository likeRepository;
    private final LikeConverter likeConverter;
    private final PostRepository postRepository;
    private final ConnectionRepository connectionRepository;
    private final ConnectionService connectionService;
    private final PostConverter postConverter;

    @Autowired
    public HomeService(LikeRepository likeRepository, LikeConverter likeConverter, PostRepository postRepository, ConnectionRepository connectionRepository, ConnectionService connectionService, ConnectionService connectionService1, PostConverter postConverter){
        this.likeRepository = likeRepository;
        this.likeConverter = likeConverter;
        this.postRepository = postRepository;
        this.connectionRepository = connectionRepository;
        this.connectionService = connectionService1;
        this.postConverter = postConverter;
    }

    public List<PostDto> getHomePosts() {
        Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();
        //etsi pairnoume ola ta Post tou user pou einai loggedIn
        List<Post> usersPostList = postRepository.findAllByCreatorIdOrderByPostDateDesc(logedUserId);
        //edw exoume olous tous filous tou
        List<UserDto> friendsList = connectionService.getFriendsToUserDto();
        List<Long> friendsIdList = friendsList.stream().map(UserDto::getUserId).collect(Collectors.toList());

        List<Long> idFriendsList = connectionService.getFriendsToUserDto().stream().map(UserDto::getUserId).collect(Collectors.toList());

        for(int i =0; i< friendsList.size() ;i++){
            usersPostList.addAll(postRepository.findAllByCreatorIdOrderByPostDateDesc(friendsList.get(i).getUserId())); //gia kathe friend prosthetoume kai aytounou ta Post sthn telikh lista
        }
        //ola ta post ths vashs
        List<Post> allPostsInDatabase = postRepository.findAll();
        for (int i =0; i < allPostsInDatabase.size() ; i++){
            //Gia kathe post pairnoume ta Like tou
            List<Like> postLikesList = likeRepository.findAllByPostId(allPostsInDatabase.get(i).getPostId());
            //Gia kathe post pairnoume thn lista twn UserId pou ekanan like kai anhkoun sta userId twn filwn tou
            List<Long> userIdLikes = likeRepository.findAllByPostId(allPostsInDatabase.get(i).getPostId()).stream().map(Like::getUserId).filter(x-> idFriendsList.contains(x) ).collect(Collectors.toList());

            if(userIdLikes.size() >0 ) //an ekane estw kai enas filos tou like sto post ayto
            {
                if(!usersPostList.contains(allPostsInDatabase.get(i))) { //an den periexete hdh to post to prosthetw
                    usersPostList.add(allPostsInDatabase.get(i));

                }
            }
        }

        return usersPostList.stream().map(postConverter::toPostDto).collect(Collectors.toList());
    }
}
