package com.linkedin.service;

import com.linkedin.converter.PostConverter;
import com.linkedin.entities.database.Like;
import com.linkedin.entities.database.Post;
import com.linkedin.entities.database.repo.LikeRepository;
import com.linkedin.entities.database.repo.PostRepository;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.security.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

	private final LikeRepository likeRepository;
	private final PostRepository postRepository;
	private final ConnectionService connectionService;
	private final PostConverter postConverter;

	@Autowired
	public HomeService(LikeRepository likeRepository, PostRepository postRepository, ConnectionService connectionService1, PostConverter postConverter) {
		this.likeRepository = likeRepository;
		this.postRepository = postRepository;
		this.connectionService = connectionService1;
		this.postConverter = postConverter;
	}

	public List<PostDto> getHomePosts() {
		Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();
		//etsi pairnoume ola ta Post tou user pou einai loggedIn
		List<Post> usersPostList = postRepository.findAllByCreatorIdOrderByPostDateDesc(logedUserId);
		//edw exoume olous tous filous tou
		List<UserSimpleDto> friendsList = connectionService.getFriendsToUserSimpleDto(logedUserId);
		List<Long> friendsIdList = friendsList.stream().map(UserSimpleDto::getUserId).collect(Collectors.toList());

		List<Long> idFriendsList = connectionService.getFriendsToUserSimpleDto(logedUserId).stream().map(UserSimpleDto::getUserId).collect(Collectors.toList());

		for (UserSimpleDto usersFriendsList : friendsList) {
			usersPostList.addAll(postRepository.findAllByCreatorIdOrderByPostDateDesc(usersFriendsList.getUserId())); //gia kathe friend prosthetoume kai aytounou ta Post sthn telikh lista
		}
		//ola ta post ths vashs
		List<Post> allPostsInDatabase = postRepository.findAll();
		for (Post anAllPostsInDatabase : allPostsInDatabase) {
			//Gia kathe post pairnoume ta Like tou
			List<Like> postLikesList = likeRepository.findAllByPostId(anAllPostsInDatabase.getPostId());
			//Gia kathe post pairnoume thn lista twn UserId pou ekanan like kai anhkoun sta userId twn filwn tou
			List<Long> userIdLikes = likeRepository.findAllByPostId(anAllPostsInDatabase.getPostId()).stream().map(Like::getUserId).filter(x -> idFriendsList.contains(x)).collect(Collectors.toList());

			//an ekane estw kai enas filos tou like sto post ayto
			if (userIdLikes.size() > 0) {
				if (!usersPostList.contains(anAllPostsInDatabase)) { //an den periexete hdh to post to prosthetw
					usersPostList.add(anAllPostsInDatabase);

				}
			}
		}

		return usersPostList.stream().map(postConverter::toPostDto)
				.sorted(Comparator.comparing(PostDto::getPostDate).reversed())
				.collect(Collectors.toList());
	}
}
