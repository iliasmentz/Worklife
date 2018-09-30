package com.linkedin.entities.model.recommendation;

import jdk.nashorn.internal.parser.DateParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoBo {
	private Long userId;
	private Long postId;
//	private Date dateOfPost;
	private Long postCreatorId;
	private Long numberOfLikes;
	private Long numberOfComments;
	private Long isUserAndPosterFriends;

//	public UserInfoBo(Long userId,
//					  Long postId,
////					  String dateOfPost,
//					  Long postCreatorId,
//					  Long numberOfLikes,
//					  Long numberOfComments,
//					  Long isUserAndPosterFriends) {
//		System.out.println("this is called");
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
//		this.userId = userId;
//		this.postId = postId;
//		try {
//			this.dateOfPost = simpleDateFormat.parse(dateOfPost);
//		} catch (ParseException e) {
//			this.dateOfPost = new Date();
//			e.printStackTrace();
//		}
//		this.postCreatorId = postCreatorId;
//		this.numberOfLikes = numberOfLikes;
//		this.numberOfComments = numberOfComments;
//		this.isUserAndPosterFriends = isUserAndPosterFriends;
//	}
}